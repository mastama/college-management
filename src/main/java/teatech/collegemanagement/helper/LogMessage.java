package teatech.collegemanagement.helper;

import org.springframework.stereotype.Component;

@Component
public class LogMessage {
    public static class Student {
        public static String StudentCreated() {
            return "Student with " + "created successfully";
        }
    }
}