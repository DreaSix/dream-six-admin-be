package dream6.example.demo.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class WinnerDetailsRequest {

    private String winnerName;
    private Integer matchId; // Assuming MatchDetails is identified by an ID
    private MultipartFile winnerImage; // Base64 encoded image
    private Double winnerAmount;
    private Integer playerId;

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public MultipartFile getWinnerImage() {
        return winnerImage;
    }

    public void setWinnerImage(MultipartFile winnerImage) {
        this.winnerImage = winnerImage;
    }

    public Double getWinnerAmount() {
        return winnerAmount;
    }

    public void setWinnerAmount(Double winnerAmount) {
        this.winnerAmount = winnerAmount;
    }
}
