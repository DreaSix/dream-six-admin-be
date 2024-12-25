package dream6.example.demo.Service;

import dream6.example.demo.Repository.MatchDetailsRepository;
import dream6.example.demo.Repository.PlayerDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamPlayerDetailsServiceImpl implements TeamPlayerDetailsService{

    @Autowired
    private MatchDetailsRepository matchDetailsRepository;

    @Autowired
    private PlayerDetailsRepository playerDetailsRepository;

}
