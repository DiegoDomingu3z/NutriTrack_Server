package com.nutritrack.client.services;

import java.time.Duration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutritrack.client.dto.FatSecretFoodIdResponse;
import com.nutritrack.client.models.FatSecretFood;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class FatSecretFoodService {
    private final FatSecretTokenService tokenService;
    private final RedisTemplate<String, String> redisTemplate;
    private final WebClient webClient;

    // The base URL for FatSecret's API endpoints (e.g., https://platform.fatsecret.com)
    @Value("${fatsecret.apiBaseUrl}")
    private String apiBaseUrl;

    public FatSecretFoodService(FatSecretTokenService tokenService, RedisTemplate<String, String> redisTemplate, WebClient.Builder webClientBuilder) {
        this.tokenService = tokenService;
        this.redisTemplate = redisTemplate;
        this.webClient = webClientBuilder.build();
    }

    /**
     * Retrieves food details by barcode.
     * First checks Redis cache; if not found, it calls FatSecret APIs.
     */
    public FatSecretFood getFoodByBarcode(String barcode) throws JsonProcessingException {
        String cacheKey = "food:" + barcode;
        ObjectMapper mapper = new ObjectMapper();
        // Try to retrieve from Redis cache
        String cachedFoodJson = redisTemplate.opsForValue().get(cacheKey);
        if (cachedFoodJson != null) {
            System.out.println("RETURNED CACHED FOOD: " + cachedFoodJson);
            FatSecretFood cachedFood = mapper.readValue(cachedFoodJson, FatSecretFood.class);
            return cachedFood;
        }

        // Not cached: call FatSecret API to get the food ID
        String foodId = getFoodIdForBarcode(barcode);
        // Now fetch detailed food data using the food ID
        FatSecretFood foodDetails = getFoodDetails(foodId);

        // Cache the food details for future requests (e.g., for 24 hours)
        String foodJsonToCache = mapper.writeValueAsString(foodDetails);
        redisTemplate.opsForValue().set("food:" + barcode, foodJsonToCache, Duration.ofHours(24));
        System.out.println("RETURNED FOOD FROM FATSECRET API: " + foodJsonToCache);
        return foodDetails;
    }

    // Calls the food.find_id_for_barcode endpoint to retrieve the food ID.
    private String getFoodIdForBarcode(String barcode) {
        String url = apiBaseUrl + "/barcode/find-by-id/v1?barcode=" + barcode + "&format=json";
        String accessToken = tokenService.getAccessToken();

        FatSecretFoodIdResponse response = webClient.get()
                .uri(url)
                .header("Authorization", "Bearer " + accessToken)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(FatSecretFoodIdResponse.class)
                .block();
        if (response == null || response.getFoodId() == null) {
            throw new RuntimeException("Failed to fetch food ID for barcode: " + barcode);
        }
        return response.getFoodId();
    }

    // Calls the food.get endpoint to retrieve detailed food information.
    private FatSecretFood getFoodDetails(String foodId) {
        String url = apiBaseUrl + "/v4?food_id=" + foodId + "&format=json&include_food_images=true&flag_default_serving=true";
        String accessToken = tokenService.getAccessToken();

        FatSecretFood foodDetails = webClient.get()
                .uri(url)
                .header("Authorization", "Bearer " + accessToken)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(FatSecretFood.class)
                .block();

        if (foodDetails == null) {
            throw new RuntimeException("Failed to fetch food details for food ID: " + foodId);
        }
        return foodDetails;
    }



}
