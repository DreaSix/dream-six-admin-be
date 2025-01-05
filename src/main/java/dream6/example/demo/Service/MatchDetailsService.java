package dream6.example.demo.Service;

import dream6.example.demo.Entity.MatchDetails;
import dream6.example.demo.dto.request.MatchDetailsRequest;
import dream6.example.demo.dto.response.MatchDetailsResponse;

import java.io.IOException;
import java.util.List;

public interface MatchDetailsService {
    MatchDetails saveMatchDetails(MatchDetailsRequest matchDetailsRequest) throws IOException;

    List<MatchDetailsResponse> getMatchDetails();

    MatchDetailsResponse getMatchDetailsById(Integer matchId) throws Exception;
}
