package dream6.example.demo.Repository;

import dream6.example.demo.Entity.UserDetailsConf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsConfigRepository extends JpaRepository<UserDetailsConf, Integer> {
    boolean existsByUsername(String tempUserName);

    Optional<UserDetailsConf> findByUsernameAndPassword(String userName, String password);

    Optional<UserDetailsConf> findByUsername(String username);

}
