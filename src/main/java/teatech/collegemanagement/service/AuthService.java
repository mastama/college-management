package teatech.collegemanagement.service;

import teatech.collegemanagement.dto.LoginRequest;
import teatech.collegemanagement.dto.RegisterRequest;
import teatech.collegemanagement.dto.UserDto;

public interface AuthService {
    UserDto createUser(RegisterRequest request);

    Object login(LoginRequest request);
}
