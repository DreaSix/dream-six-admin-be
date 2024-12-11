package dream6.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "MATCH_DETAILS")
public class MatchDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer matchId;

    @Lob
    @Column(name = "MATCH_IMAGE")
    private byte[] matchImage;

    @Column(name = "MATCH_NAME")
    private String matchName;

    @Column(name = "MATCH_TIME")
    private String matchTime;

    @Column(name = "COUNTDOWN_START_TIME")
    private LocalDateTime countdownStartTime;

    @Column(name = "COUNTDOWN_END_TIME")
    private LocalDateTime countdownEndTime;

    @Column(name = "TEAM_1_NAME")
    private String teamOneName;

    @Column(name = "TEAM_2_NAME")
    private String teamTwoName;

    @Column(name = "MATCH_ACTION")
    private String matchAction;
}
