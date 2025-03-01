package com.nutritrack.client.middleware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author Diego Dominguez
 * @date: Feb 16 2025
 * @version 1.0
 * @description: Configuration class for Spring Security.
 * <p>
 * This class defines the security policies for the application, including:
 * <ul>
 *     <li>Disabling CSRF protection for API requests.</li>
 *     <li>Allowing public access to `/api/{controller}/public/**` endpoints.</li>
 *     <li>Enforcing authentication for `/api/{controller}/secure/**` endpoints.</li>
 *     <li>Integrating Firebase authentication and rate limiting.</li>
 * </ul>
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final RateLimiterCleanupService rateLimiterCleanupService;

    /**
     * Constructor for injecting the {@link RateLimiterCleanupService}.
     *
     * @param rateLimiterCleanupService The service responsible for managing rate limiters.
     */
    public WebSecurityConfig(RateLimiterCleanupService rateLimiterCleanupService) {
        this.rateLimiterCleanupService = rateLimiterCleanupService;
    }

    /**
     * Defines the security filter chain for handling authentication and authorization rules.
     *
     * @param http The {@link HttpSecurity} object used to configure security settings.
     * @return A {@link SecurityFilterChain} that enforces the defined security policies.
     * @throws Exception If an error occurs while configuring security.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF protection (optional)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(new AntPathRequestMatcher("/api/*/public/**")).permitAll()
                        .requestMatchers("/api/*/secure/**").authenticated() // ✅ Protect these endpoints
                        .anyRequest().permitAll()
                )
                .addFilterBefore(new FirebaseAuthAndRateLimit(rateLimiterCleanupService),
                        UsernamePasswordAuthenticationFilter.class); // ✅ Register filter manually

        return http.build();
    }
}
