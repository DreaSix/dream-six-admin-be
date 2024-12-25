package dream6.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "WINNER_DETAILS")
public class WinnerDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "WINNER_NAME")
    private String winnerName;

    @JoinColumn(name = "MATCH_ID")
    @ManyToOne
    private MatchDetails matchDetails;

    @Column(name = "WINNER_IMAGE")
    private byte[] winnerImage;

    @Column(name = "WINNER_AMOUNT")
    private Double winnerAmount;

    @JoinColumn(name = "PLAYER_ID")
    @ManyToOne
    private  PlayerDetails playerDetails;


}
