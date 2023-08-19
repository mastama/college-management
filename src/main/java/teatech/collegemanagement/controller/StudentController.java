package teatech.collegemanagement.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teatech.collegemanagement.dto.ResponseService;
import teatech.collegemanagement.dto.StudentRequest;
import teatech.collegemanagement.service.StudentService;
import teatech.collegemanagement.util.Constant;

@Slf4j
@RequestMapping("/api/students")
@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/create")
    public ResponseService createStudent(@RequestBody StudentRequest request) {
        log.info("Incoming /create: {} ", request.getNim());
        ResponseService responseService;
        try {
            responseService = studentService.createStudent(request);
            if (!(responseService == null)) {
                responseService.setResponseCode(Constant.RESPONSE.APPROVED.getCode());
                responseService.setResponseDesc(Constant.RESPONSE.APPROVED.getDescription());
                responseService.setData(responseService.getData()); //kenapa terus null
            } else {
                return new ResponseService(Constant.RESPONSE.HTTP_INTERNAL_ERROR.getCode(), Constant.RESPONSE.HTTP_INTERNAL_ERROR.getDescription(), null);
            }
            log.info("Outgoing /create: {} ", request.getNim());
            return responseService;
        } catch (Exception e) {
            log.error("Error occured while creating student: {}", e.getMessage());
        }
        return new ResponseService(Constant.RESPONSE.HTTP_INTERNAL_ERROR.getCode(), Constant.RESPONSE.HTTP_INTERNAL_ERROR.getDescription(), null);
    }
}
