package dream6.example.demo.Service;


import dream6.example.demo.dto.request.PlayerDetailsRequest;
import dream6.example.demo.dto.request.TeamPlayerDetailsRequest;
import dream6.example.demo.dto.response.PlayerDetailsResponse;

import java.io.IOException;
import java.util.List;

public interface PlayerDetailsService {
    void saveMatchDetails(PlayerDetailsRequest playerDetailsRequest) throws IOException;

    List<PlayerDetailsResponse> getMatchDetails(PlayerDetailsRequest playerDetailsRequest);

    void saveTeamPlayerDetails(TeamPlayerDetailsRequest teamPlayerDetailsRequest) throws Exception;
}
