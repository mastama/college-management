package teatech.collegemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teatech.collegemanagement.entity.Student;


@Repository
public interface StudentRepository extends JpaRepository<Student, String> {

    boolean existsByNim(String nim);
    Object findByNim(String nim);
}
