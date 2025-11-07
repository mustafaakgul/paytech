package org.fintech.paytech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] AUTH_WHITELIST = {
        "/api/v1/auth/register", // Registration endpoint
        "/api/v1/auth/login",    // Login endpoint
        "/v3/api-docs/**",       // OpenAPI documentation
        "/swagger-ui/**",        // Swagger UI resources
        "/swagger-ui.html"       // Swagger UI HTML page
    };

    // private final AuthenticationProvider authenticationProvider;
    private final JwtAuthFilter jwtAuthFilter;
    private final CorsConfig corsConfig;


    /*public SecurityConfig(AuthenticationProvider authenticationProvider, JwtAuthFilter jwtAuthFilter) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthFilter = jwtAuthFilter;
    }*/
    public SecurityConfig(JwtAuthFilter jwtAuthFilter, CorsConfig corsConfig) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.corsConfig = corsConfig;
    }

    // Http featured security configuration for the application, runs every http request
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(c -> c.configurationSource(corsConfig))
                // This is for stateless session management, we are not using sessions in this application cuz of RESTful and not MVC architecture
                .csrf(AbstractHttpConfigurer::disable) // Not recommended for production, we use as not getting warning in development
                // Expecting JWT token in the Authorization header in all requests cuz of not using stateful session management
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(AUTH_WHITELIST).permitAll() // Allow public access to these endpoints
                                .anyRequest().authenticated()
                )
                // Session management is fully stateless, we are not storing sessions in this application
                .sessionManagement(sessionManagement ->
                        sessionManagement
                            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                //.authenticationProvider(authenticationProvider)
                // Custom JWT authentication filter to validate JWT token in the Authorization header
                // This filter will be executed before the UsernamePasswordAuthenticationFilter
                // Parse token and get user details from db and put it in the Context Common
                // Run jwtAuthFilter before UsernamePasswordAuthenticationFilter cuz of parsing jwt and put it in the context
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
