package teatech.collegemanagement.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import teatech.collegemanagement.dto.LoginRequest;
import teatech.collegemanagement.dto.RegisterRequest;
import teatech.collegemanagement.dto.UserDto;
import teatech.collegemanagement.entity.User;
import teatech.collegemanagement.repository.UserRepository;
import teatech.collegemanagement.service.AuthService;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    @Override
    public UserDto createUser(RegisterRequest request) {
        log.info("Start createUser: {} ", request.getUsername());

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already registered");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        user.setNoHandphone(request.getNoHandphone());
        User createdUser = userRepository.save(user);

        UserDto userDto = new UserDto();
        userDto.setUsername(createdUser.getUsername());
        userDto.setEmail(createdUser.getEmail());
        userDto.setPassword(createdUser.getPassword());
        userDto.setNoHandphone(createdUser.getNoHandphone());

        log.info("End createUser: {} ", request.getUsername());
        return userDto;
    }

    @Override
    public Object login(LoginRequest request) {
        log.info("Start login: {} ", request.getUsername());

        User user = userRepository.findFirstByUsername(request.getUsername());
        if (user == null || user.equals("")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "username or password is wrong");
        } else if (BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            userRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "username or password is wrong");
        }
        log.info("End login: {} ", request.getUsername());
        return user;
    }
}
