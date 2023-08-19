package teatech.collegemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import teatech.collegemanagement.entity.Student;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseService {
    private String responseCode;
    private String responseDesc;
    private Student data;
}
