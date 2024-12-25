package dream6.example.demo.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtUtils {

    @Value("${dreamSix.app.jwtSecret}")
    private String jwtSecret;

    @Value("${dreamSix.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Value("${dreamSix.app.jwtRefreshExpirationMs}")
    private int jwtRefreshExpirationMs;

    /**
     * Generate a JWT token.
     * @param authentication the authentication object containing user details
     * @return the generated JWT token
     */
    public String generateJwtToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Date createdDate = new Date();
        Date expirationDate = new Date(createdDate.getTime() + jwtExpirationMs * 1000);

        return Jwts.builder()
                .setSubject(user.getUsername())  // set the username as subject
                .setIssuedAt(createdDate)        // set the issue date
                .setExpiration(expirationDate)   // set expiration date
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)  // sign the JWT token
                .compact();
    }

    /**
     * Generate a refresh JWT token.
     * @param authentication the authentication object containing user details
     * @return the generated refresh JWT token
     */
    public String generateRefreshJwtToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Date createdDate = new Date();
        Date expirationDate = new Date(createdDate.getTime() + jwtRefreshExpirationMs * 1000);

        return Jwts.builder()
                .setSubject(user.getUsername())  // set the username as subject
                .setIssuedAt(createdDate)        // set the issue date
                .setExpiration(expirationDate)   // set expiration date
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)  // sign the refresh token
                .compact();
    }

    /**
     * Get the signing key.
     * @return the signing key used to sign JWT tokens
     */
    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Get the username from the JWT token.
     * @param token the JWT token
     * @return the username stored in the token
     */
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtSecret.getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Validate the JWT token.
     * @param authToken the JWT token to validate
     * @return true if the token is valid, false otherwise
     */
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
//            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
//            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
//            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
//            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
