package dream6.example.demo.dto.response;

import lombok.Data;

@Data
public class MatchPlayerDetailsResponse {

    private Integer playerId;
    private String playerName;
    private String countryName;
    private String playerImage; // Base64 encoded image
    private String teamName;
    private MatchDetailsResponse matchDetails;

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

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

    public String getPlayerImage() {
        return playerImage;
    }

    public void setPlayerImage(String playerImage) {
        this.playerImage = playerImage;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public MatchDetailsResponse getMatchDetails() {
        return matchDetails;
    }

    public void setMatchDetails(MatchDetailsResponse matchDetails) {
        this.matchDetails = matchDetails;
    }
}