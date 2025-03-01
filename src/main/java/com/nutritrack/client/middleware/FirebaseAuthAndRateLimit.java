package com.nutritrack.client.middleware;
import com.google.common.util.concurrent.RateLimiter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Collections;
import java.io.IOException;

/**
 * @author: Diego Dominguez
 * @date: Feb 16 2025
 * @version 1.0
 * @description: Service class for handling Firebase authentication and rate limiting.
 * This class verifies Firebase tokens and applies request rate limits.
 */
public class FirebaseAuthAndRateLimit extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(FirebaseAuthAndRateLimit.class);
    private final RateLimiterCleanupService rateLimiterCleanupService;

    /**
     * Constructor for FirebaseAuthAndRateLimit.
     *
     * @param rateLimiterCleanupService The service responsible for managing rate limiters.
     */
    public FirebaseAuthAndRateLimit(RateLimiterCleanupService rateLimiterCleanupService) {
        this.rateLimiterCleanupService = rateLimiterCleanupService;
    }

    /**
     * Processes authentication and rate limiting for secure API endpoints.
     * Only applies filtering if the request matches `/api/{controller}/secure/**`.
     *
     * @param request The incoming HTTP request.
     * @param response The outgoing HTTP response.
     * @param filterChain The filter chain to continue processing.
     * @throws ServletException If an error occurs during processing.
     * @throws IOException If an input/output error occurs.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();

        // ✅ Skip filtering if the endpoint is public
        if (!path.matches("^/api/[^/]+/secure/.*$")) {
            logger.info("Skipping FirebaseAuthAndRateLimit for public endpoint: {}", path);
            filterChain.doFilter(request, response);
            return;
        }


        // ✅ Extract the token
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            logger.warn("Missing or invalid Authorization header");
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Missing or invalid Authorization header.");
            return;
        }

        String idToken = authHeader.substring("Bearer ".length());
        String uid;
        try {
            uid = FirebaseService.verifyToken(idToken);
        } catch (Exception e) {
            logger.error("Token verification failed", e);
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid or expired token.");
            return;
        }

        // ✅ Store the UID in request for downstream use
        request.setAttribute("uid", uid);

        // ✅ Set user authentication in Spring Security Context
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(uid, null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authentication);


        // ✅ Retrieve or create the rate limiter for this user
        RateLimiter rateLimiter = rateLimiterCleanupService.getRateLimiter(uid);

        if (!rateLimiter.tryAcquire()) {
            logger.warn("Rate limit exceeded for UID: {}", uid);
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("Too many requests, please try again later.");
            return;
        }

        // ✅ Proceed with request
        filterChain.doFilter(request, response);
    }
}
