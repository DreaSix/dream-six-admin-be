package dream6.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "TEAM_PLAYER_DETAILS")
public class TeamPlayerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer teamPlayerDetailsId;

    @Column(name = "TEAM_NAME")
    private String teamName;

    @ManyToOne
    @JoinColumn(name = "PLAYERS_DETAILS")
    private PlayerDetails player;

    @ManyToOne
    @JoinColumn(name = "MATCH_DETAILS")
    private MatchDetails matchDetails;

}
