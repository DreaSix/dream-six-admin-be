package dream6.example.demo.Repository;

import dream6.example.demo.Entity.MatchDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchDetailsRepository extends JpaRepository<MatchDetails, Integer> {
}
