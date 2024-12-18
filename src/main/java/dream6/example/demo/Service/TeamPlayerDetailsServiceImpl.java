package dream6.example.demo.Service;

import dream6.example.demo.Entity.MatchDetails;
import dream6.example.demo.Entity.PlayerDetails;
import dream6.example.demo.Entity.TeamPlayerDetails;
import dream6.example.demo.Repository.MatchDetailsRepository;
import dream6.example.demo.Repository.PlayerDetailsRepository;
import dream6.example.demo.dto.request.TeamPlayerDetailsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TeamPlayerDetailsServiceImpl implements TeamPlayerDetailsService{

    @Autowired
    private MatchDetailsRepository matchDetailsRepository;

    @Autowired
    private PlayerDetailsRepository playerDetailsRepository;

}
