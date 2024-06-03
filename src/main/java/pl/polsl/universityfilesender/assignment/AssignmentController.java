package pl.polsl.universityfilesender.assignment;


import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.polsl.universityfilesender.assignment.dto.AssignmentSaveRequest;
import pl.polsl.universityfilesender.assignment.dto.DetailedAssignmentDto;
import pl.polsl.universityfilesender.assignment.dto.StudentAndAssignmentStatusDto;
import pl.polsl.universityfilesender.assignment.dto.SubmitAssignmentRequest;
import pl.polsl.universityfilesender.user.User;
import pl.polsl.universityfilesender.userassignmentrelationship.StudentAssignmentRelationshipService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {
    private final AssignmentService assignmentService;

    private final StudentAssignmentRelationshipService studentAssignmentRelationshipService;


    public AssignmentController(AssignmentService assignmentService, StudentAssignmentRelationshipService studentAssignmentRelationshipService) {
        this.assignmentService = assignmentService;
        this.studentAssignmentRelationshipService = studentAssignmentRelationshipService;
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

    @PutMapping("/{assignmentId}")
    @PreAuthorize("hasRole('ROLE_TEACHER') and @userService.isAssignmentOwner(authentication, #assignmentId)")
    public ResponseEntity<Void> updateAssignment(@PathVariable("assignmentId") Long assignmentId, @Valid @ModelAttribute AssignmentSaveRequest assignmentSaveRequest) {
        assignmentService.updateAssignment(assignmentId, assignmentSaveRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{assignmentId}/download")
//    @PreAuthorize("@userService.isAssignmentOwner(authentication, #assignmentId)")
    public ResponseEntity<ByteArrayResource> downloadAssignment(@PathVariable("assignmentId") Long assignmentId) {
        ByteArrayResource resource = assignmentService.downloadAssignment(assignmentId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"assignment_files.zip\"")
                .header(HttpHeaders.CONTENT_TYPE, "application/zip")
                .body(resource);
    }

    @PutMapping("/{assignmentId}/submit")
    @PreAuthorize("hasRole('ROLE_STUDENT') and @userService.isAssignmentOwner(authentication, #assignmentId)")
    public ResponseEntity<Void> submitAssignment(@PathVariable("assignmentId") Long assignmentId,@Valid @ModelAttribute SubmitAssignmentRequest submitAssignmentRequest, @AuthenticationPrincipal User student) {
        studentAssignmentRelationshipService.submitAssignment(student, submitAssignmentRequest, assignmentId);
        return ResponseEntity.noContent().build();
    }




}
