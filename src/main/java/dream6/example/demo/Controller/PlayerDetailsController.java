package dream6.example.demo.Controller;

import dream6.example.demo.Service.PlayerDetailsService;
import dream6.example.demo.dto.request.PlayerDetailsRequest;
import dream6.example.demo.dto.request.TeamPlayerDetailsRequest;
import dream6.example.demo.dto.response.ApiResponse;
import dream6.example.demo.dto.response.MatchPlayerDetailsResponse;
import dream6.example.demo.dto.response.PlayerDetailsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/player-details")
public class PlayerDetailsController {
    @Autowired
    private PlayerDetailsService playerDetailsService;

    @PostMapping(value = "/save")
    public ResponseEntity<ApiResponse> savePlayerDetails(@ModelAttribute PlayerDetailsRequest playerDetailsRequest) throws IOException {

        playerDetailsService.saveMatchDetails(playerDetailsRequest);

        ApiResponse apiResponse = ApiResponse.builder()
                .status(200)
                .responseTime(LocalDateTime.now())
                .data("create Match successfully!")
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/get")
    public ResponseEntity<ApiResponse> getPlayerDetails(@ModelAttribute PlayerDetailsRequest playerDetailsRequest) throws IOException {

        List<PlayerDetailsResponse> playerDetailsResponseList =  playerDetailsService.getMatchDetails(playerDetailsRequest);

        ApiResponse apiResponse = ApiResponse.builder()
                .status(200)
                .responseTime(LocalDateTime.now())
                .data(playerDetailsResponseList)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping(value = "/save-team")
    public ResponseEntity<ApiResponse> saveTeamPlayerDetails(@RequestBody TeamPlayerDetailsRequest teamPlayerDetailsRequest) throws Exception {

        playerDetailsService.saveTeamPlayerDetails(teamPlayerDetailsRequest);

        ApiResponse apiResponse = ApiResponse.builder()
                .status(200)
                .responseTime(LocalDateTime.now())
                .data("create Players for team successfully!")
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ApiResponse> getMatchPlayers(@PathVariable Integer id) throws Exception {

        List<MatchPlayerDetailsResponse> playerDetailsResponseList =  playerDetailsService.getMatchTeamPlayers(id);

        ApiResponse apiResponse = ApiResponse.builder()
                .status(200)
                .responseTime(LocalDateTime.now())
                .data("create Players for team successfully!")
                .build();

        return ResponseEntity.ok(apiResponse);
    }


}
