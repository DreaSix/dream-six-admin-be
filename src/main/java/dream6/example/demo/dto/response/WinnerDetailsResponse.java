package dream6.example.demo.dto.response;


import lombok.Data;

@Data
public class WinnerDetailsResponse {
    private Integer id;
    private String winnerName;
    private MatchDetailsResponse matchDetails;
    private String winnerImage; // Base64 encoded image
    private Double winnerAmount;
    private PlayerDetailsResponse playerDetailsResponse;
}