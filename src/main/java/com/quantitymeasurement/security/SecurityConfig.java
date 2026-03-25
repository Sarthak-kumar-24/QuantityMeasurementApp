package com.quantitymeasurement.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
 * =========================================================
 * Security Configuration
 * =========================================================
 *
 * This class configures:
 * - Authentication (login)
 * - Authorization (who can access what)
 * - JWT filter integration
 * 
 * Client (Swagger/Postman)
 *       |
 *  Request hits SecurityFilterChain
 *       |
 *  JwtFilter runs FIRST
 *       |
 *  Check token
 *       |
 *  If valid    ->  allow request
 *  If invalid  ->  reject (401/403)
 *       |
 *  Controller executes
 */


@Configuration // Marks this as Spring configuration class
@EnableWebSecurity // Enables Spring Security in the application
public class SecurityConfig {
	
    /*
     * JwtFilter:
     * Custom filter that checks JWT token in every request
     */
    @Autowired
    private JwtFilter jwtFilter;

    /*
     * =========================================================
     * PASSWORD ENCODER
     * =========================================================
     *
     * Used to encrypt passwords before saving in DB
     * and to match passwords during login
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // strong hashing algorithm
    }

    /*
     * =========================================================
     * AUTHENTICATION MANAGER
     * =========================================================
     *
     * Used to authenticate user credentials during login
     *
     * Internally:
     * - Calls CustomUserDetailsService
     * - Compares password using PasswordEncoder
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /*
     * =========================================================
     * SECURITY FILTER CHAIN
     * =========================================================
     *
     * This defines:
     * - Which APIs are public
     * - Which APIs require authentication
     * - Where JWT filter should run
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
        /*
         * Disable CSRF (not needed for REST APIs)
         */
        .csrf(csrf -> csrf.disable())
        /*
         * Define authorization rules
         */
        .authorizeHttpRequests(auth -> auth
                /*
                 * Public APIs (no authentication needed)
                 */
                .requestMatchers(
                        "/auth/**",          // login & register
                        "/swagger-ui/**",    // Swagger UI
                        "/v3/api-docs/**"    // API docs
                ).permitAll()
                /*
                 * All other APIs require authentication
                 */
                .anyRequest().authenticated()
        );

        /*
         * Add JWT filter before Spring's default authentication filter
         *
         * This ensures:
         * JWT is checked BEFORE request reaches controller
         */
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        /*
         * Build and return security configuration
         */
        return http.build();
    }
}