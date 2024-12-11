package dream6.example.demo.Repository;

import dream6.example.demo.Entity.UserDetailsConfig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsConfigRepository extends JpaRepository<UserDetailsConfig, Integer> {
    boolean existsByGuid(String guid);

    boolean existsByUserName(String tempUserName);

    Optional<UserDetailsConfig> findByUserNameAndPassword(String userName, String password);

    Optional<UserDetailsConfig> findByUserNameAndEntityId(String userNameBranchToken, String userNameBranchToken1);
}
