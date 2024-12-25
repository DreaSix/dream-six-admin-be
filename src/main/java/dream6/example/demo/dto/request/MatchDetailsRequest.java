package dream6.example.demo.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter
@Setter
public class MatchDetailsRequest {

    private String matchName;
    private String matchTime;
    private String countDownEndTime;
    private String teamOneName;
    private String teamTwoName;
    private String matchAction;
    private MultipartFile matchImage;

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

    public String getCountDownEndTime() {
        return countDownEndTime;
    }

    public void setCountDownEndTime(String countDownEndTime) {
        this.countDownEndTime = countDownEndTime;
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

    public MultipartFile getMatchImage() {
        return matchImage;
    }

    public void setMatchImage(MultipartFile matchImage) {
        this.matchImage = matchImage;
    }
}
