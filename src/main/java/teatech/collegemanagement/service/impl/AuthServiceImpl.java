package teatech.collegemanagement.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import teatech.collegemanagement.dto.RegisterRequest;
import teatech.collegemanagement.dto.UserDto;
import teatech.collegemanagement.entity.User;
import teatech.collegemanagement.repository.UserRepository;
import teatech.collegemanagement.security.BCrypt;
import teatech.collegemanagement.service.AuthService;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    @Override
    public UserDto createUser(RegisterRequest request) {
        log.info("Start createUser: {} ", request.getUsername());
        if (userRepository.existsByUsername(request.getUsername()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already registered");

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setNoHandphone(request.getNoHandphone());
        User createduser = userRepository.save(user);

        UserDto userDto = new UserDto();
        userDto.setUsername(createduser.getUsername());
        userDto.setEmail(createduser.getEmail());
        userDto.setPassword(createduser.getPassword());
        userDto.setNoHandphone(createduser.getNoHandphone());

        log.info("End createUser: {} ", request.getUsername());
        return userDto;
    }
}
