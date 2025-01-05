package dream6.example.demo.Controller;

import dream6.example.demo.Entity.UserDetailsConf;
import dream6.example.demo.Entity.Users;
import dream6.example.demo.Repository.UsersRepository;
import dream6.example.demo.Service.UserDetailsServiceImpl;
import dream6.example.demo.dto.request.UserRequest;
import dream6.example.demo.dto.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/api/user")
@RestController
public class UserDetailsController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UsersRepository usersRepository;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> createUser(@RequestBody UserRequest request) throws Exception {

        userDetailsService.addUser(request);

        ApiResponse apiResponse = ApiResponse.builder()
                .status(200)
                .responseTime(LocalDateTime.now())
                .data("User created successfully")
                .build();


        return ResponseEntity.ok().body(apiResponse);
    }

    @PostMapping(value = "/createUser")
    public ResponseEntity<UserDetailsConf> createUserDetailsConfig(@RequestParam Integer userId) {

        Users users = usersRepository.findByUserId(userId);

        UserDetailsConf userDetails = userDetailsService.createUser(users);

        return ResponseEntity.ok().body(userDetails);
    }

    @GetMapping(value = "/get-users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getUserDetailsConfig() throws Exception {

        List<UserDetailsConf> usersList = userDetailsService.getUserDetailsConfig();

        ApiResponse apiResponse = ApiResponse.builder()
                .status(200)
                .responseTime(LocalDateTime.now())
                .data(usersList)
                .build();


        return ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> getUsers() throws Exception {

        List<Users> usersList = userDetailsService.getUsers();

        ApiResponse apiResponse = ApiResponse.builder()
                .status(200)
                .responseTime(LocalDateTime.now())
                .data(usersList)
                .build();


        return ResponseEntity.ok().body(apiResponse);
    }
}
