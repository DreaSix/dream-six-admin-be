package dream6.example.demo.Repository;

import dream6.example.demo.Entity.UserDetailsConf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsConfigRepository extends JpaRepository<UserDetailsConf, Integer> {
    boolean existsByGuid(String guid);

    boolean existsByUsername(String tempUserName);

    Optional<UserDetailsConf> findByUsernameAndPassword(String userName, String password);

    Optional<UserDetailsConf> findByUsernameAndEntityId(String userNameBranchToken, Integer entityId);

//    Optional<UserDetailsConf> findByUserNameAndBranchCode(String userNameBranchToken, String userNameBranchToken1);
}
