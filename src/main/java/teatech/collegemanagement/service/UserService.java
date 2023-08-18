package teatech.collegemanagement.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import teatech.collegemanagement.repository.UserRepository;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {
    private final UserRepository userRepo;
}
