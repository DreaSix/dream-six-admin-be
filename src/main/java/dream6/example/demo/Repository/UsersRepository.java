package dream6.example.demo.Repository;

import dream6.example.demo.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByUserId(Integer userId);

    Users findByContactNo(String contactNumber);
}
