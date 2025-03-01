package com.nutritrack.client.middleware;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Service responsible for managing user-specific rate limiters.
 * This class provides a per-user rate limiter and periodically cleans up inactive limiters.
 *
 * <p>Each user (identified by UID) gets a unique rate limiter that allows
 * only a specific number of requests per second.</p>
 *
 * @author Diego Dominguez
 * @date: Feb 16 2025
 * @version 1.0
 */
@Component
public class RateLimiterCleanupService {
    /**
     * Concurrent map to store rate limiters per user (UID).
     */
    private final ConcurrentMap<String, RateLimiter> userRateLimiters = new ConcurrentHashMap<>();

    /**
     * Retrieves or creates a rate limiter for a given user UID.
     * If a rate limiter does not exist for the user, a new one is created
     * allowing 1 request per second.
     *
     * @param uid The unique identifier of the user.
     * @return The rate limiter associated with the given UID.
     */
    public RateLimiter getRateLimiter(String uid) {
        return userRateLimiters.computeIfAbsent(uid, key -> RateLimiter.create(1.0)); // 1 request/sec
    }

    /**
     * Periodically removes inactive rate limiters from memory.
     * Runs every 10 minutes and removes any rate limiter with a rate of zero.
     */
    @Scheduled(fixedRate = 10 * 60 * 1000) // Runs every 10 minutes
    public void cleanupRateLimiters() {
        userRateLimiters.entrySet().removeIf(entry -> entry.getValue().getRate() == 0);
    }
}
