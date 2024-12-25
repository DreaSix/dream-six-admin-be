package dream6.example.demo.Controller;

import dream6.example.demo.Service.WinnerDetailsService;
import dream6.example.demo.dto.request.WinnerDetailsRequest;
import dream6.example.demo.dto.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/winner-details")
public class WinnerDetailsController {

    @Autowired
    private WinnerDetailsService winnerDetailsService;

    @PostMapping
    public ResponseEntity<ApiResponse> createWinner(@RequestBody WinnerDetailsRequest request) throws Exception {

        winnerDetailsService.createWinner(request);

        ApiResponse apiResponse = ApiResponse.builder()
                .status(200)
                .responseTime(LocalDateTime.now())
                .data("Winner created successfully")
                .build();


        return ResponseEntity.ok().body(apiResponse);
    }
}
