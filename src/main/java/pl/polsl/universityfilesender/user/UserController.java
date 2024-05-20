package pl.polsl.universityfilesender.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.universityfilesender.course.CourseService;
import pl.polsl.universityfilesender.course.dto.CourseDto;
import pl.polsl.universityfilesender.user.dto.UserDto;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final CourseService courseService;


    public UserController(UserService userService, CourseService courseService) {
        this.userService = userService;
        this.courseService = courseService;
    }


    @GetMapping("/current")
    public ResponseEntity<UserDto> getCurrentUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userService.getUser(user));
    }


    @GetMapping("/current/courses")
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<List<CourseDto>> getCurrentUserCourses(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(courseService.getCoursesByTeacher(user));
    }


}
