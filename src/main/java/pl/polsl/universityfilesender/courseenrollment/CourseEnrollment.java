package pl.polsl.universityfilesender.courseenrollment;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.polsl.universityfilesender.course.Course;
import pl.polsl.universityfilesender.user.User;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder

public class CourseEnrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @Enumerated(EnumType.STRING)
    private Status status;

    public CourseEnrollment() {

    }

    public CourseEnrollment(Long id, User student, Course course, Status status) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.status = status;
    }

    public enum Status {
        PENDING, ACCEPTED
    }

}
