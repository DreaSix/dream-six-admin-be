package dream6.example.demo.Mapper;

import dream6.example.demo.Entity.MatchDetails;
import dream6.example.demo.dto.response.MatchDetailsResponse;

import java.util.Base64;

public class ModelMapper {

    public MatchDetailsResponse convertEntityToMatchDetailsResponse(MatchDetails matchDetails){
        MatchDetailsResponse matchDetailsResponse = new MatchDetailsResponse();
        matchDetailsResponse.setMatchId(matchDetails.getMatchId());
        matchDetailsResponse.setMatchName(matchDetails.getMatchName());
        matchDetailsResponse.setMatchTime(matchDetails.getMatchTime());
        matchDetailsResponse.setCountdownEndTime(matchDetails.getCountdownEndTime());
        matchDetailsResponse.setCountdownStartTime(matchDetails.getCountdownStartTime());
        matchDetailsResponse.setMatchAction(matchDetails.getMatchAction());
        matchDetailsResponse.setTeamOneName(matchDetails.getTeamOneName());
        matchDetailsResponse.setTeamTwoName(matchDetails.getTeamTwoName());
        matchDetailsResponse.setOptionCompleted(matchDetails.getOptionCompleted());
        byte[] imageBytes = matchDetails.getMatchImage();

        String base64Image = Base64.getEncoder().encodeToString(imageBytes);

        matchDetailsResponse.setMatchImage(base64Image);
        return matchDetailsResponse;
    }
}
