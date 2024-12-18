package dream6.example.demo.Service;

import dream6.example.demo.Entity.MatchDetails;
import dream6.example.demo.Entity.PlayerDetails;
import dream6.example.demo.Entity.TeamPlayerDetails;
import dream6.example.demo.Repository.MatchDetailsRepository;
import dream6.example.demo.Repository.PlayerDetailsRepository;
import dream6.example.demo.Repository.TeamPlayerDetailsRepository;
import dream6.example.demo.dto.request.PlayerDetailsRequest;
import dream6.example.demo.dto.request.TeamPlayerDetailsRequest;
import dream6.example.demo.dto.response.PlayerDetailsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerDetailsServiceImpl implements PlayerDetailsService{

    @Autowired
    private PlayerDetailsRepository playerDetailsRepository;

    @Autowired
    private MatchDetailsRepository matchDetailsRepository;

    @Autowired
    private TeamPlayerDetailsRepository teamPlayerDetailsRepository;

    @Override
    public void saveMatchDetails(PlayerDetailsRequest playerDetailsRequest) throws IOException {
        PlayerDetails playerDetails = new PlayerDetails();

        playerDetails.setPlayerName(playerDetailsRequest.getPlayerName());
        playerDetails.setCountryName(playerDetailsRequest.getCountryName());
        MultipartFile playerImage = playerDetailsRequest.getPlayerImage();
        if (playerImage != null && !playerImage.isEmpty()) {
            // Convert the image file to a byte array
            byte[] imageBytes = playerImage.getBytes();
            // Set the byte array to matchDetails
            playerDetails.setPlayerImage(imageBytes);
        }

        playerDetailsRepository.save(playerDetails);
    }

    @Override
    public List<PlayerDetailsResponse> getMatchDetails(PlayerDetailsRequest playerDetailsRequest) {
        List<PlayerDetails> playerDetails = playerDetailsRepository.findAll();
        List<PlayerDetailsResponse> playerDetailsResponse = new ArrayList<>();
        for (PlayerDetails playerDetails1 : playerDetails){
            PlayerDetailsResponse playerDetailsResponse1 = new PlayerDetailsResponse();
            playerDetailsResponse1.setPlayerId(playerDetails1.getPlayerId());
            playerDetailsResponse1.setPlayerName(playerDetails1.getPlayerName());
            playerDetailsResponse1.setCountryName(playerDetails1.getCountryName());
            byte[] imageBytes = playerDetails1.getPlayerImage();

            String base64Image = Base64.getEncoder().encodeToString(imageBytes);

            playerDetailsResponse1.setPlayerImage(base64Image);

            playerDetailsResponse.add(playerDetailsResponse1);
        }
        return playerDetailsResponse;
    }

    @Override
    public void saveTeamPlayerDetails(TeamPlayerDetailsRequest teamPlayerDetailsRequest) throws Exception {
        Optional<MatchDetails> optionalMatchDetails = matchDetailsRepository.findById(teamPlayerDetailsRequest.getMatchId());
        if (optionalMatchDetails.isEmpty()){
            throw new Exception("No match is found with this id");
        }
        List<TeamPlayerDetails> teamPlayerDetailsList = new ArrayList<>();
        List<PlayerDetails> playerDetails = playerDetailsRepository.findAllByPlayerIds(teamPlayerDetailsRequest.getPlayerIds());
        MatchDetails matchDetails1 = optionalMatchDetails.get();
        for (PlayerDetails playerDetails1 : playerDetails){
            TeamPlayerDetails teamPlayerDetails = new TeamPlayerDetails();
            teamPlayerDetails.setTeamName(teamPlayerDetailsRequest.getTeamName());
            teamPlayerDetails.setPlayer(playerDetails1);
            teamPlayerDetails.setMatchDetails(matchDetails1);

            teamPlayerDetailsList.add(teamPlayerDetails);
        }

        teamPlayerDetailsRepository.saveAll(teamPlayerDetailsList);

    }
}
