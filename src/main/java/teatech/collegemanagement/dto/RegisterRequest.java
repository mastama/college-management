package teatech.collegemanagement.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String noHandphone;
    private String email;
    private String password;
}
