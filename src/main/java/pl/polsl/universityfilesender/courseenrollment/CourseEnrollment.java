package pl.polsl.universityfilesender.courseenrollment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.polsl.universityfilesender.course.Course;
import pl.polsl.universityfilesender.user.User;

@Entity
@Getter
@Setter
public class CourseEnrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        PENDING, ACCEPTED
    }

}
