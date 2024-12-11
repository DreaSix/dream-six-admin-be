package dream6.example.demo.dto.response;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    //status, data(Actual Response)(any time), response time

    private Integer status;

    private LocalDateTime responseTime;

    private Object data;
    private String message;

}

