package teatech.collegemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseService {
    private String responseCode;
    private String responseDesc;
    private String data;
}
