package dream6.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name= "USER_DETAILS_CONFIG")
public class UserDetailsConfig extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROLE")
    private String role;

    @Column(name = "GUID", nullable = false, unique = true, length = 7)
    private String guid;

    @Column(name = "ENTITY_ID")
    private Integer entityId;


}
