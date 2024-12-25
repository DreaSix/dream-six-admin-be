package dream6.example.demo.Service;

import dream6.example.demo.Entity.MatchDetails;
import dream6.example.demo.Entity.PlayerDetails;
import dream6.example.demo.Entity.WinnerDetails;
import dream6.example.demo.Repository.MatchDetailsRepository;
import dream6.example.demo.Repository.PlayerDetailsRepository;
import dream6.example.demo.Repository.WinnerDetailsRepository;
import dream6.example.demo.dto.request.WinnerDetailsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WinnerDetailsServiceImpl implements WinnerDetailsService{

    @Autowired
    private MatchDetailsRepository matchDetailsRepository;

    @Autowired
    private PlayerDetailsRepository playerDetailsRepository;

    @Autowired
    private WinnerDetailsRepository winnerDetailsRepository;

    @Override
    public void createWinner(WinnerDetailsRequest request) throws Exception {
        Optional<MatchDetails> matchDetails = matchDetailsRepository.findById(request.getMatchId());
        Optional<PlayerDetails> playerDetails = playerDetailsRepository.findById(request.getPlayerId());
        if (matchDetails.isEmpty()){
            throw new Exception("Match details not found with this id");
        }

        if (playerDetails.isEmpty()){
            throw new Exception("Player details not found with this id");
        }

        WinnerDetails winnerDetails = new WinnerDetails();
        winnerDetails.setWinnerName(request.getWinnerName());
        winnerDetails.setWinnerAmount(request.getWinnerAmount());
        winnerDetails.setMatchDetails(matchDetails.get());
        winnerDetails.setPlayerDetails(playerDetails.get());
        if (request.getWinnerImage() != null && !request.getWinnerImage().isEmpty()) {
            // Convert the image file to a byte array
            byte[] imageBytes = request.getWinnerImage().getBytes();
            winnerDetails.setWinnerImage(imageBytes);
        }

        winnerDetailsRepository.save(winnerDetails);

    }
}
