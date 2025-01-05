package dream6.example.demo.config;


import dream6.example.demo.Service.UserDetailsServiceImpl;
import dream6.example.demo.constants.JwtConstants;
import dream6.example.demo.utils.JwtUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtTokenUtil;

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        log.info("leeeeela");
//    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        // Bypass JWT check for specific endpoints
        return path.startsWith("/api/user/createUser");
    }


    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        String header = req.getHeader(JwtConstants.HEADER_STRING); // Typically "Authorization"
        String username = null;
        String authToken = null;

        if (header != null && header.startsWith(JwtConstants.TOKEN_PREFIX)) { // TOKEN_PREFIX is "Bearer "
            authToken = header.substring(JwtConstants.TOKEN_PREFIX.length()); // Remove "Bearer " prefix

            try {
                username = jwtTokenUtil.getUserNameFromJwtToken(authToken); // Extract username from JWT
            } catch (IllegalArgumentException e) {
                logger.error("An error occurred while getting username from token", e);
            } catch (ExpiredJwtException e) {
                logger.warn("The token is expired and not valid anymore", e);
            } catch (SignatureException e) {
                logger.error("Authentication Failed: Invalid token signature", e);
            }
        } else {
            logger.warn("Authorization header is missing or does not start with Bearer");
        }

        // Proceed if username is extracted and the security context is not already authenticated
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtTokenUtil.validateJwtToken(authToken)) { // Validate the JWT token

                // Create an authentication object and set it in the security context
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));

                logger.info("Authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                logger.warn("JWT token validation failed");
            }
        }

        // Proceed with the filter chain
        chain.doFilter(req, res);
    }

}
