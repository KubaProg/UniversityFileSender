package pl.polsl.universityfilesender.course;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.polsl.universityfilesender.assignment.AssignmentService;
import pl.polsl.universityfilesender.assignment.dto.AssignmentGetDto;
import pl.polsl.universityfilesender.assignment.dto.AssignmentSaveRequest;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    private final AssignmentService assignmentService;

    public CourseController(CourseService courseService, AssignmentService assignmentService) {
        this.courseService = courseService;
        this.assignmentService = assignmentService;
    }


    @DeleteMapping("/{courseId}")
    @PreAuthorize("@courseService.isCourseOwner(authentication, #courseId) and hasRole('ROLE_TEACHER')")
    public ResponseEntity<Void> deleteCourse(@PathVariable("courseId") Long courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();

    }


    @GetMapping("/{courseId}/assignments")
    @PreAuthorize("@courseService.isCourseOwner(authentication, #courseId) and hasRole('ROLE_TEACHER')")
    public ResponseEntity<List<AssignmentGetDto>> getAssignmentsByCourseId(@PathVariable("courseId") Long courseId) {
        return ResponseEntity.ok(assignmentService.getAllAssignmentsByCourseId(courseId));
    }

    @PostMapping(path = "/{courseId}/assignments", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("@courseService.isCourseOwner(authentication, #courseId) and hasRole('ROLE_TEACHER')")
    public ResponseEntity<AssignmentGetDto> saveAssignment(@PathVariable("courseId") Long courseId,@Valid @ModelAttribute AssignmentSaveRequest assignmentSaveRequest) {
        return ResponseEntity.created(URI.create("/api/assignments/" + assignmentService.saveAssignment(courseId, assignmentSaveRequest).getId())).build();
    }


}
