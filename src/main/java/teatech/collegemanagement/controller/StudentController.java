package teatech.collegemanagement.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import teatech.collegemanagement.dto.ResponseService;
import teatech.collegemanagement.dto.StudentRequest;
import teatech.collegemanagement.dto.UpdateStudentRequest;
import teatech.collegemanagement.helper.LogMessage;
import teatech.collegemanagement.repository.StudentRepository;
import teatech.collegemanagement.service.StudentService;
import teatech.collegemanagement.util.Constant;

@Slf4j
@RequestMapping("/api/students")
@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final StudentRepository studentRepository;
    @PostMapping("/create")
    public ResponseService createStudent(@RequestBody StudentRequest request) {
        log.info("Incoming /create: {} ", request.getNim());
        ResponseService responseService = new ResponseService();
        try {
            ResponseService serviceStudent = studentService.createStudent(request);
            if (studentRepository.existsByNim(request.getNim())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "student with nim " + request.getNim() + " already exists");
            }
            if (serviceStudent != null) {
                responseService.setResponseCode(Constant.RESPONSE.APPROVED.getCode());
                responseService.setResponseDesc(Constant.RESPONSE.APPROVED.getDescription());
                responseService.setData(LogMessage.Student.StudentCreated());
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

    @PutMapping("/{nim}")
    public ResponseService updateStudent(@PathVariable String nim, @RequestBody UpdateStudentRequest request) {
        log.info("Incoming /put: {}", request.getNim());
        ResponseService responseService = new ResponseService();
        try {
            ResponseService updateStudent = studentService.updateStudent(nim, request);
            if (updateStudent == null) {
                responseService.setResponseCode(Constant.RESPONSE.APPROVED.getCode());
                responseService.setResponseDesc(Constant.RESPONSE.APPROVED.getDescription());
                responseService.setData(LogMessage.Student.updateStudent(nim));
            } else {
                return new ResponseService(Constant.RESPONSE.HTTP_INTERNAL_ERROR.getCode(), Constant.RESPONSE.HTTP_INTERNAL_ERROR.getDescription(), null);
            }
        } catch (Exception e) {
            throw e;
        }
        return responseService;
    }
}
