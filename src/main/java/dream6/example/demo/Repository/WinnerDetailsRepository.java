package dream6.example.demo.Repository;

import dream6.example.demo.Entity.WinnerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WinnerDetailsRepository extends JpaRepository<WinnerDetails, Integer> {
}
