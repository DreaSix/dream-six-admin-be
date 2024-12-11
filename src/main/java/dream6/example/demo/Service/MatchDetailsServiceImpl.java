package dream6.example.demo.Service;

import dream6.example.demo.Entity.MatchDetails;
import dream6.example.demo.Repository.MatchDetailsRepository;
import dream6.example.demo.dto.request.MatchDetailsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class MatchDetailsServiceImpl implements MatchDetailsService{

    @Autowired
    private MatchDetailsRepository matchDetailsRepository;

    @Override
    public MatchDetails saveMatchDetails(MatchDetailsRequest matchDetailsRequest) throws IOException {
        MatchDetails matchDetails = new MatchDetails();
        matchDetails.setMatchName(matchDetailsRequest.getMatchName());
        matchDetails.setMatchAction(matchDetailsRequest.getMatchAction());
        matchDetails.setTeamOneName(matchDetailsRequest.getTeamOneName());
        matchDetails.setTeamTwoName(matchDetailsRequest.getTeamTwoName());

        LocalDateTime countDownStartTime = LocalDateTime.now();
        matchDetails.setCountdownStartTime(countDownStartTime);

        String countDownEndTimeStr = matchDetailsRequest.getCountDownEndTime();  // Assuming the format is string
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Adjust this format to match the request format
        LocalDateTime countDownEndTime = LocalDateTime.parse(countDownEndTimeStr, formatter);

        matchDetails.setCountdownEndTime(countDownEndTime);
        matchDetails.setMatchTime(matchDetailsRequest.getMatchTime());
        MultipartFile matchImage = matchDetailsRequest.getMatchImage();
        if (matchImage != null && !matchImage.isEmpty()) {
            // Convert the image file to a byte array
            byte[] imageBytes = matchImage.getBytes();
            // Set the byte array to matchDetails
            matchDetails.setMatchImage(imageBytes);
        }

        matchDetailsRepository.save(matchDetails);

        return matchDetails;
    }
}
