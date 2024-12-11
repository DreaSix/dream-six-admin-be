package dream6.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "PLAYER_DETAILS")
public class PlayerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer playerId;

    @Column(name = "PLAYER_NAME")
    private String playerName;

    @Column(name = "COUNTRY_NAME")
    private String countryName;

    @Lob
    @Column(name = "PLAYER_IMAGE")
    private byte[] playerImage;

}
