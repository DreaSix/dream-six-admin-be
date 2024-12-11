package dream6.example.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String accessToken;
    private String type = "Bearer";
    private String username;
    private List<String> roles;
    private String refreshToken;
    private Integer entityId;

    public JwtResponse(String accessToken, String refreshToken, String username, List<String> roles,
                       Integer entityId) {
        this.accessToken = accessToken;
        this.username = username;
        this.roles = roles;
        this.refreshToken = refreshToken;
        this.entityId = entityId;
    }
}
