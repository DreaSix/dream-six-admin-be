package dream6.example.demo.Controller;

import dream6.example.demo.Service.TeamPlayerDetailsService;
import dream6.example.demo.dto.request.PlayerDetailsRequest;
import dream6.example.demo.dto.request.TeamPlayerDetailsRequest;
import dream6.example.demo.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/team-player-details")
public class TeamPlayerDetailsController {
    @Autowired
    private TeamPlayerDetailsService teamPlayerDetailsService;

}