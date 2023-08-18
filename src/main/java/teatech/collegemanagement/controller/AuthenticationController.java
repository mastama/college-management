package teatech.collegemanagement.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import teatech.collegemanagement.dto.AuthenticationRequest;
import teatech.collegemanagement.dto.AuthenticationResponse;
import teatech.collegemanagement.service.impl.UserDetailServiceImpl;
import teatech.collegemanagement.util.JwtUtil;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserDetailServiceImpl userDetailService;
    private final JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public AuthenticationResponse createAuthentication(@RequestBody AuthenticationRequest request, HttpServletResponse response) throws IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password");
        } catch (DisabledException disabledException) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not found. u need register first!");
            return null;
        }
        final UserDetails userDetails = userDetailService.loadUserByUsername(request.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        return new AuthenticationResponse(jwt);
    }
}
