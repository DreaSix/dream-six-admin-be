package dream6.example.demo.Repository;

import dream6.example.demo.Entity.TeamPlayerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamPlayerDetailsRepository extends JpaRepository<TeamPlayerDetails, Integer> {
}
