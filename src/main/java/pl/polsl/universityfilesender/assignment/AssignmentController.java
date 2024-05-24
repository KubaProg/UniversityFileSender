package pl.polsl.universityfilesender.assignment;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.polsl.universityfilesender.assignment.dto.DetailedAssignmentDto;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @GetMapping("/{assignmentId}")
    public ResponseEntity<DetailedAssignmentDto> getDetailedAssignment(@PathVariable("assignmentId") Long assignmentId) {
        return ResponseEntity.ok(assignmentService.getDetailedAssignment(assignmentId));
    }

}
