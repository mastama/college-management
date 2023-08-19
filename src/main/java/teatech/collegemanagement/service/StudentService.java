package teatech.collegemanagement.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import teatech.collegemanagement.dto.ResponseService;
import teatech.collegemanagement.dto.StudentRequest;
import teatech.collegemanagement.entity.Student;
import teatech.collegemanagement.repository.StudentRepository;

import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public ResponseService createStudent(StudentRequest request) {
        log.info("Start createStudent: {}", request.getNim());
        ResponseService responseService = new ResponseService();
        try {
            if (studentRepository.existsByNim(request.getNim())) {
                log.error("Student already exists");
            }
            //instance object student
            Student studentReq = new Student();
            studentReq.setFirstName(request.getFirstName());
            studentReq.setLastName(request.getLastName());
            studentReq.setNim(request.getNim());
            studentReq.setPhoneNumber(request.getPhoneNumber());
            studentReq.setCreatedDate(getCurrentDate());
            studentReq.setUpdatedDate(getCurrentDate());

            studentRepository.save(studentReq);
            log.info("End createStudent: {}", studentReq.getNim());
        } catch (Exception e) {
            throw e;
        }
        return responseService;
    }

    private static Date getCurrentDate() {
        return new Date();
    }
}
