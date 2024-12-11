package dream6.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "USER_DETAILS")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Integer userId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CONTACT_NO")
    private String contactNo;

    @Column(name = "ROLE")
    private String role;
}
