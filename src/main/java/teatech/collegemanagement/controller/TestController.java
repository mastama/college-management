package teatech.collegemanagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from JWT TOKEN";
    }

    @GetMapping("/test")
    public String testUsingTokenFromLogin() {
        return "This is using token from login";
    }
}
