package dream6.example.demo.Controller;

import dream6.example.demo.Service.TeamPlayerDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/api/team-player-details")
public class TeamPlayerDetailsController {
    @Autowired
    private TeamPlayerDetailsService teamPlayerDetailsService;

}
