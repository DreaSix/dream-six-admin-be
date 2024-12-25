package dream6.example.demo.Repository;

import dream6.example.demo.Entity.MatchDetails;
import dream6.example.demo.Entity.TeamPlayerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamPlayerDetailsRepository extends JpaRepository<TeamPlayerDetails, Integer> {
    List<TeamPlayerDetails> findByMatchDetails(MatchDetails matchDetails);
}
