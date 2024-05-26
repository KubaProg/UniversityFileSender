package pl.polsl.universityfilesender.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.polsl.universityfilesender.course.CourseService;
import pl.polsl.universityfilesender.course.dto.CourseDto;
import pl.polsl.universityfilesender.course.dto.SaveCourseRequest;
import pl.polsl.universityfilesender.user.dto.UserDto;

import javax.validation.Valid;
import java.net.URI;
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

    @GetMapping({"/{userId}"})
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }


    @GetMapping("/current/courses")
    public ResponseEntity<List<CourseDto>> getCurrentUserCourses(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(courseService.getCoursesByUser(user));
    }


    @PostMapping("/current/courses")
    @PreAuthorize("hasRole('ROLE_TEACHER')")
    public ResponseEntity<CourseDto> saveCourse(@Valid @RequestBody SaveCourseRequest saveCourseRequest, @AuthenticationPrincipal User user) {
        return ResponseEntity.created(URI.create("/api/courses/" + courseService.saveCourse(user, saveCourseRequest).getId())).build();
    }


}
