package dream6.example.demo.Controller;

import dream6.example.demo.Entity.UserDetailsConf;
import dream6.example.demo.Entity.Users;
import dream6.example.demo.Repository.UserDetailsConfigRepository;
import dream6.example.demo.Repository.UsersRepository;
import dream6.example.demo.Service.UserDetailsServiceImpl;
import dream6.example.demo.dto.request.LoginRequest;
import dream6.example.demo.dto.response.JwtResponse;
import dream6.example.demo.modal.UserDetailsImpl;
import dream6.example.demo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsConfigRepository userDetailsConfigRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @PostMapping(value = "/login")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();

        try {
            // Authenticate user with the provided credentials
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            username,
                            loginRequest.getPassword()
                    )
            );

            if (!authentication.isAuthenticated()){
                throw new Exception("Invalid username or password");
            }

            // If authentication is successful, set the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate JWT tokens
            String jwt = jwtUtils.generateJwtToken(loginRequest.getUsername());
//            String refreshToken = jwtUtils.generateRefreshJwtToken(authentication);

            // Extract user details
            User user = (User) authentication.getPrincipal();
            List<String> roles = user.getAuthorities().stream()
                    .map(authority -> authority.getAuthority())
                    .collect(Collectors.toList());

            // Retrieve user information for the response
            Optional<UserDetailsConf> userDetailsOpt = userDetailsConfigRepository.findByUsername(username);
            if (userDetailsOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("User details could not be fetched");
            }

            UserDetailsConf userDetails = userDetailsOpt.get();

            Map<String, Object> response = new HashMap<>();
            response.put("jwtToken", jwt);
            response.put("userId", userDetails.getUserId() );

            return ResponseEntity.ok(response);
        } catch (BadCredentialsException ex) {
            // Handle invalid credentials
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        } catch (Exception ex) {
            // Handle general exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
        }
    }

}
