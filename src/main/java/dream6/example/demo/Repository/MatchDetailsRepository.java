package dream6.example.demo.Repository;

import dream6.example.demo.Entity.MatchDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchDetailsRepository extends JpaRepository<MatchDetails, Integer> {
}
