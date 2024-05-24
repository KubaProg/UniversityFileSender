package pl.polsl.universityfilesender.assignment;

import org.springframework.stereotype.Service;
import pl.polsl.universityfilesender.assignment.dto.AssignmentGetDto;
import pl.polsl.universityfilesender.assignment.dto.DetailedAssignmentDto;
import pl.polsl.universityfilesender.exception.EntityNotFoundException;

import java.util.List;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final AssignmentMapper assignmentMapper;

    public AssignmentService(AssignmentRepository assignmentRepository, AssignmentMapper assignmentMapper) {
        this.assignmentRepository = assignmentRepository;
        this.assignmentMapper = assignmentMapper;
    }

    public List<AssignmentGetDto> getAllAssignmentsByCourseId(Long courseId) {
        List<Assignment> assignments = assignmentRepository.findAllByCourseId(courseId);
        return assignmentMapper.toDtoList(assignments);
    }

    public DetailedAssignmentDto getDetailedAssignment(Long assignmentId) {
        Assignment assignment = getAssignmentById(assignmentId);
        return assignmentMapper.toDetailedDto(assignment);
    }

    private Assignment getAssignmentById(Long assignmentId) {
        return assignmentRepository.findById(assignmentId).orElseThrow(() -> new EntityNotFoundException(Assignment.class, "id", assignmentId.toString()));
    }
}
