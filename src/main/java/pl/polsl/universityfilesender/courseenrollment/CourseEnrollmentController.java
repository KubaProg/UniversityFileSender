package pl.polsl.universityfilesender.courseenrollment;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/course-enrollments")
public class CourseEnrollmentController {

    private final CourseEnrollmentService courseEnrollmentService;

    public CourseEnrollmentController(CourseEnrollmentService courseEnrollmentService) {
        this.courseEnrollmentService = courseEnrollmentService;
    }

    @PutMapping("/{enrollmentId}/accept")
    public void acceptEnrollment(@PathVariable("enrollmentId") Long enrollmentId) {
        courseEnrollmentService.acceptEnrollment(enrollmentId);
    }

}
