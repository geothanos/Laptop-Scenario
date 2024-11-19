package gr.knowledge.induction.security.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.util.StringUtils;

import gr.knowledge.induction.security.service.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Class extends {@link OncePerRequestFilter} to ensure the filter is applied once per request.
 * 
 */
public class AuthTokenFilter extends OncePerRequestFilter{
    
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    // Initialize a logger from JwtUtils.class to catch errors
    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    // Method to intercept an an HTTP request to verify the JWT and setAuthentication of the user
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            // Retrieve the JWT
            String jwt = parseJwt(request);
            // Check if the JWT is not null and its signature is valid
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                // Get the username from the Claims JWT subject
                String username = jwtUtils.getUsernameFromJwtToken(jwt);

                // Retrieve the principal (all user's Data) in a userDetails object
                UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
                // "authentication" will represent an authentication token. The principal (userDetails) are null because they are not needed
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, // principal (user "account" details)
                        null, // don't include principal
                        userDetails.getAuthorities() // Retrieve the roles of the user
                    );

                // Add additional details in the "authentication" token
                // Build the details (eg. remote address, session ID) of the current request and add it to the "authentication" token
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Set the "authentication" token in the securityContext. In this way, Spring
                // recognizes the user is authorized and allow him to access secure resources
                SecurityContextHolder.getContext().setAuthentication(authentication);
                // SecurityContextHolder provide access to SecurityContext that contains the principal (user details) and the granted authorities (roles)
            }
        } catch (Exception e) {
            // log any exception that may occur
            logger.error("Cannot set user authentication: {}", e);
        }

        // Pass the request and responce to the next filter in the chain or to endPoint
        filterChain.doFilter(request, response);
    }

    // Retrieve the JWT from the HttpServletRequest if it is not empty and contains the "Bearer" prefix in its "Authorization" Header
    private String parseJwt(HttpServletRequest request) {
        // Get the value of the "Authorization" header of the HttpServletRequest
        String headerAuth = request.getHeader("Authorization");

        // Check if the headerAuth has text (string is not null) and 
        // starts with the prefix "Bearer " (with a space after the word)
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            // Return the JWT without the first 7 charachters ('Bearer ' prefix)
            return headerAuth.substring(7);
        }

        return null;
    }
}
