package pl.polsl.universityfilesender.user;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.polsl.universityfilesender.course.CourseService;
import pl.polsl.universityfilesender.course.dto.CourseDto;
import pl.polsl.universityfilesender.course.dto.SaveCourseRequest;
import pl.polsl.universityfilesender.file.FileService;
import pl.polsl.universityfilesender.user.dto.UserDto;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final CourseService courseService;

    private final FileService fileService;


    public UserController(UserService userService, CourseService courseService, FileService fileService) {
        this.userService = userService;
        this.courseService = courseService;
        this.fileService = fileService;
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

    @GetMapping("/{userId}/assignments/{assignmentId}/download")
    @PreAuthorize("@userService.isAssignmentOwner(authentication, #assignmentId)")
    public ResponseEntity<ByteArrayResource> downloadStudentAttachment(@PathVariable("userId") Long userId, @PathVariable("assignmentId") Long assignmentId) {
        ByteArrayResource resource = fileService.downloadAssignmentFiles(userId, assignmentId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"assignment_files.zip\"")
                .header(HttpHeaders.CONTENT_TYPE, "application/zip")
                .body(resource);
    }


    @GetMapping("/current/courses/pending")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity<List<CourseDto>> getPendingCoursesforCurrentStudent(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(courseService.getPendingCoursesForStudent(user));
    }

    @GetMapping("/current/courses/not-assigned")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity<List<CourseDto>> getNotAssignedCoursesforCurrentStudent(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(courseService.getNotAssignedCourses(user));
    }
}
