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

import java.util.List;
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

    @PostMapping(value = "/createUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDetailsConf> createUser(@RequestBody Integer userId) {

        Users users = usersRepository.findByUserId(userId);

        UserDetailsConf userDetails = userDetailsService.createUser(users);

        return ResponseEntity.ok().body(userDetails);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {

        String username = loginRequest.getUsername().toLowerCase();

        Optional<UserDetailsConf> userDetails1 = userDetailsConfigRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());

        if (userDetails1.isEmpty()) {
            // Return a response indicating that the username or password is incorrect
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }

        UserDetailsConf userDetailsConfig = userDetails1.get();

        username = userDetailsConfig.getUsername();
        String userNameBranchCodeStr = String.format("%s~%s", username, userDetailsConfig.getEntityId());

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userNameBranchCodeStr, loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            String refreshToken = jwtUtils.generateRefreshJwtToken(authentication);

            User user = (User) authentication.getPrincipal();
            UserDetailsConf userInfo = userDetailsService.findUserByUserName(userNameBranchCodeStr);

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
