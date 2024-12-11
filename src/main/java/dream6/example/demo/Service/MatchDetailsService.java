package dream6.example.demo.Service;

import dream6.example.demo.Entity.MatchDetails;
import dream6.example.demo.dto.request.MatchDetailsRequest;

import java.io.IOException;

public interface MatchDetailsService {
    MatchDetails saveMatchDetails(MatchDetailsRequest matchDetailsRequest) throws IOException;
}
