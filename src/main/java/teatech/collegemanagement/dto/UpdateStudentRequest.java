package teatech.collegemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudentRequest {
    private String firstName;
    private String lastName;
    private String nim;
    private String phoneNumber;
    private String classroomId;
}
