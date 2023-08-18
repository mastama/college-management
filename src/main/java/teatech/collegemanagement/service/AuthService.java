package teatech.collegemanagement.service;

import teatech.collegemanagement.dto.RegisterRequest;
import teatech.collegemanagement.dto.UserDto;

public interface AuthService {
    UserDto createUser(RegisterRequest request);
}
