package dream6.example.demo.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PlayerDetailsRequest {

    private String playerName;

    private String countryName;

    private MultipartFile playerImage;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public MultipartFile getPlayerImage() {
        return playerImage;
    }

    public void setPlayerImage(MultipartFile playerImage) {
        this.playerImage = playerImage;
    }
}
