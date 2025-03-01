package com.nutritrack.client.services;

import java.time.Duration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.nutritrack.client.dto.FatSecretTokenResponse;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.BodyInserters;

@Service
public class FatSecretTokenService {
    private final WebClient webClient;
    private final RedisTemplate<String, String> redisTemplate;
    private final RedissonClient redissonClient;

    // FatSecret configuration values injected from properties
    @Value("${fatsecret.clientId}")
    private String clientId;

    @Value("${fatsecret.clientSecret}")
    private String clientSecret;

    @Value("${fatsecret.tokenUrl}")
    private String tokenUrl;

    private static final String TOKEN_KEY = "fatsecret:access_token";
    private static final String LOCK_KEY = "fatsecret:lock";

    public FatSecretTokenService(WebClient.Builder webClientBuilder,
                                 RedisTemplate<String, String> redisTemplate,
                                 RedissonClient redissonClient) {
        this.webClient = webClientBuilder.build();
        this.redisTemplate = redisTemplate;
        this.redissonClient = redissonClient;
    }

    /**
     * Refresh the FatSecret access token.
     * It calls FatSecret’s token endpoint and caches the token in Redis.
     */
    public String refreshAccessToken() {
        // Prepare the form data for the token request.
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "client_credentials");
        formData.add("client_id", clientId);
        formData.add("client_secret", clientSecret);
        formData.add("scope", "basic barcode premier localization");

        FatSecretTokenResponse tokenResponse = webClient.post()
                .uri(tokenUrl)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .bodyToMono(FatSecretTokenResponse.class)
                .block();

        if (tokenResponse != null && tokenResponse.getAccessToken() != null) {
            // Cache the token with an expiration time slightly less than the token lifetime.
            redisTemplate.opsForValue().set(TOKEN_KEY,
                    tokenResponse.getAccessToken(),
                    Duration.ofSeconds(tokenResponse.getExpiresIn() - 60));
            return tokenResponse.getAccessToken();
        } else {
            throw new RuntimeException("Failed to refresh FatSecret access token");
        }
    }

    /**
     * Retrieves the current access token from Redis.
     * Uses a distributed lock to ensure that only one instance refreshes the token when necessary.
     */
    public String getAccessToken() {
        //  GET TOKEN FROM REDIS
        String token = redisTemplate.opsForValue().get(TOKEN_KEY);
        // Return the token if its not null, means no need to refresh token
        if (token != null) {
            return token;
        }

        // Acquire the distributed lock to prevent simultaneous token refreshes.
        RLock lock = redissonClient.getLock(LOCK_KEY);
        try {
            // Wait up to 10 seconds for the lock; set lease time to 60 seconds.
            if (lock.tryLock(10, 60, TimeUnit.SECONDS)) {
                try {
                    // Double-check if the token was refreshed while waiting for the lock.
                    token = redisTemplate.opsForValue().get(TOKEN_KEY);
                    if (token == null) {
                        token = refreshAccessToken();
                    }
                    return token;
                } finally {
                    lock.unlock();
                }
            } else {
                // If lock acquisition fails, wait briefly and try retrieving the token again.
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                token = redisTemplate.opsForValue().get(TOKEN_KEY);
                if (token == null) {
                    throw new RuntimeException("Failed to acquire lock and refresh token in time.");
                }
                return token;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while waiting for token refresh lock", e);
        }
    }
}
