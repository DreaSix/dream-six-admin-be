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
}
