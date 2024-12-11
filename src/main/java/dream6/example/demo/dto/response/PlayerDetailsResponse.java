package dream6.example.demo.dto.response;

import lombok.Data;

@Data
public class PlayerDetailsResponse {
    private Integer playerId;

    private String playerName;

    private String countryName;

    private String playerImage;
}
