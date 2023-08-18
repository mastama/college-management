package teatech.collegemanagement.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import teatech.collegemanagement.dto.RegisterRequest;
import teatech.collegemanagement.dto.UserDto;
import teatech.collegemanagement.service.AuthService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody RegisterRequest request) {
        log.info("Incoming /register: {} ", request.getUsername());
        if (StringUtils.isEmpty(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username cannot empty");
        } else if (StringUtils.isEmpty(request.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password cannot empty");
        } else if (StringUtils.isEmpty(request.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email cannot empty");
        } else if (StringUtils.isEmpty(request.getNoHandphone())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "NoHandphone cannor empty");
        }

        UserDto createdUser = authService.createUser(request);
        if (createdUser == null) {
            return new ResponseEntity<>("User is not created", HttpStatus.BAD_REQUEST);
        }
        log.info("Outgoing /register: {}", createdUser.getUsername());
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
