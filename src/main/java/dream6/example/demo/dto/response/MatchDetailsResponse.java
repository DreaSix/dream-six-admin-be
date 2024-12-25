package dream6.example.demo.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MatchDetailsResponse {

    private Integer matchId;
    private String matchImage;
    private String matchName;
    private String matchTime;
    private LocalDateTime countdownStartTime;
    private LocalDateTime countdownEndTime;
    private String teamOneName;
    private String teamTwoName;
    private String matchAction;
    private String optionCompleted;

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public String getMatchImage() {
        return matchImage;
    }

    public void setMatchImage(String matchImage) {
        this.matchImage = matchImage;
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public String getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(String matchTime) {
        this.matchTime = matchTime;
    }

    public LocalDateTime getCountdownStartTime() {
        return countdownStartTime;
    }

    public void setCountdownStartTime(LocalDateTime countdownStartTime) {
        this.countdownStartTime = countdownStartTime;
    }

    public LocalDateTime getCountdownEndTime() {
        return countdownEndTime;
    }

    public void setCountdownEndTime(LocalDateTime countdownEndTime) {
        this.countdownEndTime = countdownEndTime;
    }

    public String getTeamOneName() {
        return teamOneName;
    }

    public void setTeamOneName(String teamOneName) {
        this.teamOneName = teamOneName;
    }

    public String getTeamTwoName() {
        return teamTwoName;
    }

    public void setTeamTwoName(String teamTwoName) {
        this.teamTwoName = teamTwoName;
    }

    public String getMatchAction() {
        return matchAction;
    }

    public void setMatchAction(String matchAction) {
        this.matchAction = matchAction;
    }

    public String getOptionCompleted() {
        return optionCompleted;
    }

    public void setOptionCompleted(String optionCompleted) {
        this.optionCompleted = optionCompleted;
    }
}
