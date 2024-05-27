package pl.polsl.universityfilesender.assignment;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.polsl.universityfilesender.assignment.dto.DetailedAssignmentDto;
import pl.polsl.universityfilesender.assignment.dto.StudentAndAssignmentStatusDto;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {
    private final AssignmentService assignmentService;


    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }


    @GetMapping("/{assignmentId}")
    public ResponseEntity<DetailedAssignmentDto> getDetailedAssignment(@PathVariable("assignmentId") Long assignmentId) {
        return ResponseEntity.ok(assignmentService.getDetailedAssignment(assignmentId));
    }

    @GetMapping("/{assignmentId}/users")
    @PreAuthorize("hasRole('ROLE_TEACHER') and @userService.isAssignmentOwner(authentication, #assignmentId)")
    public ResponseEntity<List<StudentAndAssignmentStatusDto>> getStudentsByAssignmentId(@PathVariable("assignmentId") Long assignmentId) {
        return ResponseEntity.ok(assignmentService.getStudentsWithAssignmentStatus(assignmentId));
    }

    @DeleteMapping("/{assignmentId}")
    public ResponseEntity<Void> deleteAssignment(@PathVariable("assignmentId") Long assignmentId) {
        this.assignmentService.deleteAssignment(assignmentId);
        return ResponseEntity.noContent().build();
    }

}
