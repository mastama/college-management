package teatech.collegemanagement.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RequestToken {
    private String username;
    private String token;
    @Column(name = "token_expired_at")
    private Long tokenExpiredAt;
}
