package pl.polsl.universityfilesender.assignment;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import pl.polsl.universityfilesender.course.Course;

import java.util.Date;

@Entity
@Getter
@Setter
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "assignment_name", nullable = false)
    @NotBlank
    private String assignmentName;

    @Column(name = "description")
    private String description;


    @Column(name = "deadline_date", nullable = false)
    @NotNull
    private Date deadlineDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;
}
