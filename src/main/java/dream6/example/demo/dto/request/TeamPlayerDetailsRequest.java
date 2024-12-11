package dream6.example.demo.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class TeamPlayerDetailsRequest {
    private String teamName;
    private List<Integer> playerIds;
    private Integer matchId;

}
