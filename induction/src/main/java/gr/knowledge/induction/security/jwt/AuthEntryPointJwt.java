package gr.knowledge.induction.security.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Class implementation of {@link AuthenticationEntryPoint} interface in Spring Security. <br>
 * Commences an authentication scheme to catch any anauthorized access attempt <br> 
 * and return log message with a 401 HTTP client response
 */
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
    // Create a logget to catch error if any occurs
    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    // Override the commence method of AuthenticationEntryPoint
    // Commence an authentication sheme to catch any anauthorized access attempt and return log message with a 401 HTTP client response
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // Log authentication error message from unauthorized user access attempts
        logger.error("Unauthorized Error: {}", authException.getMessage());
        // Send to SC_UNAUTHORIZED (=401) HTTP responce code along with an error message
        // Status code 401 indicates that the request, requires HTTP authentication
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
    }
}
