package dream6.example.demo.Service;

import dream6.example.demo.dto.request.WinnerDetailsRequest;

public interface WinnerDetailsService {
    void createWinner(WinnerDetailsRequest request) throws Exception;
}
