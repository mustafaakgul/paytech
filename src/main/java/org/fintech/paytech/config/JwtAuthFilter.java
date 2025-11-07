package org.fintech.paytech.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.fintech.paytech.domain.core.user.model.User;
import org.fintech.paytech.domain.core.user.repository.UserRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

// Filter means that it will intercept the request before it reaches the controller
// Same as a middleware not exactly filter, intercept and do smt
// Runs once per request to filter the incoming requests
// Alt. way to throw exception
@Component
//@Order(1)
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;

    public JwtAuthFilter(JwtService jwtService, UserDetailsService userDetailsService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (isPublicEndpoint(request.getRequestURI())) {
            // If the request is for authentication, skip the filter
            filterChain.doFilter(request, response);
            return;
        }

        final String authorizationHeader = request.getHeader("Authorization");
        final String jwtToken;
        final String email;

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwtToken = authorizationHeader.substring(7);
        email = jwtService.extractEmail(jwtToken);

        // If the email is not null and the authentication is not set in the security context
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            if (jwtService.isTokenValid(jwtToken, userDetails)) {
                // If the token is valid, set the authentication in the security context
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        User user = findAuthenticatedUser();
        if (user == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"success\":false,\"message\":\"Error\",\"data\":null}");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private User findAuthenticatedUser(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //return userRepository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Optional<User> userCurrent = userRepository.findByEmail(userDetails.getUsername());
        return userCurrent.orElse(null);
    }

    private boolean isPublicEndpoint(String requestURI) {
        return requestURI.contains("/auth/") ||
                requestURI.contains("/public/") ||
                requestURI.equals("/");
    }
}
