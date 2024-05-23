package pl.polsl.universityfilesender.course;


import lombok.Getter;
import lombok.Setter;
import pl.polsl.universityfilesender.assignment.Assignment;
import pl.polsl.universityfilesender.courseenrollment.CourseEnrollment;
import pl.polsl.universityfilesender.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_name", nullable = false, length = 100)
    @NotBlank(message = "Course name is required")
    @Size(min = 1, max = 100, message = "Course name must be between 1 and 100 characters")
    private String courseName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "teacher_id", nullable = false)
    private User teacher;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Assignment> assignments;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseEnrollment> courseEnrollments;
}
