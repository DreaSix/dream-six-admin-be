package dream6.example.demo.Controller;

import dream6.example.demo.Entity.MatchDetails;
import dream6.example.demo.Service.MatchDetailsService;
import dream6.example.demo.dto.request.MatchDetailsRequest;
import dream6.example.demo.dto.response.ApiResponse;
import dream6.example.demo.dto.response.MatchDetailsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/match-details")
public class MatchDetailsController {

    @Autowired
    private MatchDetailsService matchDetailsService;

    @PostMapping(value = "/save")
    public ResponseEntity<ApiResponse> saveMatchDetails(@ModelAttribute MatchDetailsRequest matchDetailsRequest) throws IOException {

        MatchDetails matchDetails = matchDetailsService.saveMatchDetails(matchDetailsRequest);

        ApiResponse apiResponse = ApiResponse.builder()
                .status(200)
                .responseTime(LocalDateTime.now())
                .data(matchDetails)
                .build();

        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping(value = "/get")
    public ResponseEntity<ApiResponse> getMatchDetails() throws IOException {

        List<MatchDetailsResponse> matchDetails =  matchDetailsService.getMatchDetails();

        ApiResponse apiResponse = ApiResponse.builder()
                .status(200)
                .responseTime(LocalDateTime.now())
                .data(matchDetails)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping(value = "/{matchId}")
    public ResponseEntity<ApiResponse> getMatchDetailsById(@PathVariable Integer matchId) throws Exception {

        MatchDetailsResponse matchDetails =  matchDetailsService.getMatchDetailsById(matchId);

        ApiResponse apiResponse = ApiResponse.builder()
                .status(200)
                .responseTime(LocalDateTime.now())
                .data(matchDetails)
                .build();

        return ResponseEntity.ok(apiResponse);
    }
}
