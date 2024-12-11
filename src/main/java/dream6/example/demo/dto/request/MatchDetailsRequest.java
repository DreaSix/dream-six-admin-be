package dream6.example.demo.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MatchDetailsRequest {
    private String matchName;
    private String matchTime;
    private String countDownEndTime;
    private String teamOneName;
    private String teamTwoName;
    private String matchAction;
    private MultipartFile matchImage;
}
