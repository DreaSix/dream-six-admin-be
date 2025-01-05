package dream6.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name= "USER_DETAILS_CONFIG")
public class UserDetailsConf{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer userId;

    @Column(name = "USER_NAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "CONTACT_NO")
    private String contactNo;

    @Column(name = "ROLE")
    private String role;

}
