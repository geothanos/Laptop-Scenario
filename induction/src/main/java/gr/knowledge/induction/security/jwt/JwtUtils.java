package gr.knowledge.induction.security.jwt;

import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import gr.knowledge.induction.security.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtUtils {
    // Initialize a logger from JwtUtils.class to catch errors
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    // Secret Key to sigh the Jwt (length of key = 64)
    @Value("${knowledge.app.jwtSecret}")
    private String jwtSecret;

    // Expiration time for the JWT
    @Value("${knowledge.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    // Generate a JWS (Signed JSON Web Token using HS256 (signature algorithm)) for the authenticated User
    public String generateJwtToken(Authentication authentication){
        // Retrieve the principal (user details) from Authentication object
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        // Create and return Claims JWT (data for username, expiration date, ...)
        return Jwts.builder()
            .setSubject((userPrincipal.getUsername())) // User's username is the subject of the token
            .setIssuedAt(new Date()) // Issued at the current date
            .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)) // Expiration is set at this moment + jwtExpirationMs
            .signWith(key(), SignatureAlgorithm.HS256) // Sign with the key and the HS256 (signature algorithm)
            .compact(); // compile the JWT to a compact URL-safe string
    }

    private Key key(){
        // Decode the jwtSectet and create a cryptographic key (HMAC SHA)
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    /**
     * Get the username from the JWT
     * 
     * @param token A Claims JWT
     * @return <code>String</code> User's username (subject) of JWT
     */
    // Get the username from a token (Claims JWT)
    public String getUsernameFromJwtToken(String token){
        return Jwts.parserBuilder() // Parse JWT
            .setSigningKey(key()) // using the signing key
            .build()
            .parseClaimsJws(token) // parse the token
            .getBody() // get tokens body
            .getSubject();  // and finaly get the subject (username)
    }

    /**
     * Parses a JWS (Claims JWT) to validate the signature and log eny excpetions that may occure
     * 
     * @param authToken String representation of a JWS (Signed JSON Web Token)
     * @return <code>true</code> if the signature is valid, <code>false</code> if not and make log messages depending on the exception
     */
    public boolean validateJwtToken(String authToken){
        // Try to parse the JWS (Claims JWT) and validate the signature using parseClaimsJws()
        // If an exception occures, make a log error
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            // Log for invalid (incorrectly constructed) token
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            // Log for expired token
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            // Log for unsupported token that does not represent a Claims JWT
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            // Log for empty/null/whitespace token string
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
      
        return false;
    }

}
