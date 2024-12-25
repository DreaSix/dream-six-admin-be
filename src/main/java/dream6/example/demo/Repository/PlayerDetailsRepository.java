package dream6.example.demo.Repository;

import dream6.example.demo.Entity.PlayerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerDetailsRepository extends JpaRepository<PlayerDetails, Integer> {
    @Query("SELECT p FROM PlayerDetails p WHERE p.playerId IN :playerIds")
    List<PlayerDetails> findAllByPlayerIds(@Param("playerIds") List<Integer> playerIds);
}
