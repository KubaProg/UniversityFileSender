package pl.polsl.universityfilesender.course;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.universityfilesender.course.dto.CourseDto;
import pl.polsl.universityfilesender.user.User;

import java.util.List;


@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<List<CourseDto>> getAllCoursesByLoggedInTeacher(@AuthenticationPrincipal User teacher) {
        return ResponseEntity.ok(courseService.getAllCoursesByTeacher(teacher));
    }

}
