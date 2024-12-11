package dream6.example.demo.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity {
    @Column(name = "USER_ID")
    private Integer userId;

}
