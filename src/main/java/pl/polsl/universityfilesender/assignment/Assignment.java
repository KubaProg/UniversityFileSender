package pl.polsl.universityfilesender.assignment;


import lombok.Getter;
import lombok.Setter;
import pl.polsl.universityfilesender.course.Course;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
