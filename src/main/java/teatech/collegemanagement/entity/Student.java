package teatech.collegemanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Data
@Table(name = "tbl_student")
@Entity
public class Student {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String nim;
    @Column(name = "created_at")
    private Date createdDate;
    @Column(name = "updated_at")
    private Date updatedDate;
    @Column(name = "phone_number")
    private String phoneNumber;
}
