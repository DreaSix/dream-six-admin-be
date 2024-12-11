package dream6.example.demo.Controller;

import dream6.example.demo.Entity.UserDetailsConfig;
import dream6.example.demo.Entity.Users;
import dream6.example.demo.Repository.UserDetailsConfigRepository;
import dream6.example.demo.Repository.UsersRepository;
import dream6.example.demo.Service.UserDetailsServiceImpl;
import dream6.example.demo.dto.request.LoginRequest;
import dream6.example.demo.dto.response.JwtResponse;
import dream6.example.demo.modal.UserDetailsImpl;
import dream6.example.demo.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;


import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsConfigRepository userDetailsConfigRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping(value = "/createUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDetailsConfig> createUser(@RequestBody Integer userId) {

        Users users = usersRepository.findByUserId(userId);

        UserDetailsConfig userDetails = userDetailsService.createUser(users);

        return ResponseEntity.ok().body(userDetails);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {

        String username = loginRequest.getUserName().toLowerCase();

        Optional<UserDetailsConfig> userDetails1 = userDetailsConfigRepository.findByUserNameAndPassword(loginRequest.getUserName(), loginRequest.getPassword());

        if (userDetails1.isEmpty()) {
            // Return a response indicating that the username or password is incorrect
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        username = userDetails1.get().getUserName();
        String userNameBranchCodeStr = String.format("%s~%s", username, userDetails1.get().getEntityId());

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userNameBranchCodeStr, loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            String refreshToken = jwtUtils.generateRefreshJwtToken(authentication);

            User user = (User) authentication.getPrincipal();
            UserDetailsConfig userInfo = userDetailsService.findUserByUserName(userNameBranchCodeStr);

            UserDetailsImpl userDetails = new UserDetailsImpl(user.getUsername(), user.getPassword(), user.getAuthorities());
            List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            return ResponseEntity.ok(
                    new JwtResponse(jwt, refreshToken, userDetails.getUsername(), roles,
                            userInfo.getUserId()));
        } catch (BadCredentialsException e) {
            // Handle authentication failure
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        } catch (Exception e) {
            // Handle any other exceptions that may occur
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login");
        }
    }
}
