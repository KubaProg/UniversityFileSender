package pl.polsl.universityfilesender.course;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.polsl.universityfilesender.assignment.AssignmentService;
import pl.polsl.universityfilesender.assignment.dto.AssignmentGetDto;
import pl.polsl.universityfilesender.assignment.dto.AssignmentSaveRequest;
import pl.polsl.universityfilesender.courseenrollment.CourseEnrollmentService;
import pl.polsl.universityfilesender.courseenrollment.dto.CourseEnrollmentDetailsDto;
import pl.polsl.universityfilesender.user.User;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    private final AssignmentService assignmentService;

    private final CourseEnrollmentService courseEnrollmentService;

    public CourseController(CourseService courseService, AssignmentService assignmentService, CourseEnrollmentService courseEnrollmentService) {
        this.courseService = courseService;
        this.assignmentService = assignmentService;
        this.courseEnrollmentService = courseEnrollmentService;
    }


    @DeleteMapping("/{courseId}")
    @PreAuthorize("@courseService.isCourseOwner(authentication, #courseId) and hasRole('ROLE_TEACHER')")
    public ResponseEntity<Void> deleteCourse(@PathVariable("courseId") Long courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();

    }


    @GetMapping("/{courseId}/assignments")
    @PreAuthorize("@courseService.isCourseOwner(authentication, #courseId) or @courseService.isCourseParticipant(authentication, #courseId)")
    public ResponseEntity<List<AssignmentGetDto>> getAssignmentsByCourseId(@PathVariable("courseId") Long courseId) {
        return ResponseEntity.ok(assignmentService.getAllAssignmentsByCourseId(courseId));
    }

    @PostMapping(path = "/{courseId}/assignments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("@courseService.isCourseOwner(authentication, #courseId) and hasRole('ROLE_TEACHER')")
    public ResponseEntity<AssignmentGetDto> saveAssignment(@PathVariable("courseId") Long courseId,@Valid @ModelAttribute AssignmentSaveRequest assignmentSaveRequest) {
        return ResponseEntity.created(URI.create("/api/assignments/" + assignmentService.saveAssignment(courseId, assignmentSaveRequest).getId())).build();
    }


    @GetMapping("/{courseId}/course-enrollments/pending")
    @PreAuthorize("@courseService.isCourseOwner(authentication, #courseId) and hasRole('ROLE_TEACHER')")
    public ResponseEntity<List<CourseEnrollmentDetailsDto>> getPendingEnrollmentsForCourse(@PathVariable("courseId") Long courseId) {
        return ResponseEntity.ok(courseEnrollmentService.getPendingEnrollmentsForCourse(courseId));
    }


    @PostMapping("/{courseId}/course-enrollments")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public ResponseEntity<Void> createPendingEnrollment(@PathVariable("courseId") Long courseId,
                                                        @AuthenticationPrincipal User student) {
        CourseEnrollmentDetailsDto enrollment = courseEnrollmentService.createPendingEnrollment(courseId, student);
        return ResponseEntity.created(URI.create("/api/course-enrollments/" + enrollment.getEnrollmentId())).build();
    }


}
