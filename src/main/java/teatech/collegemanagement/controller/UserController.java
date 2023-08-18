package teatech.collegemanagement.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teatech.collegemanagement.dto.RegisterRequest;
import teatech.collegemanagement.dto.UserDto;
import teatech.collegemanagement.service.AuthService;
import teatech.collegemanagement.service.UserService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody RegisterRequest request) {
        log.info("Incoming /api/register: {} ", request.getUsername());
        UserDto createdUser = authService.createUser(request);
        if (createdUser == null)
            return new ResponseEntity<>("User is not created", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
