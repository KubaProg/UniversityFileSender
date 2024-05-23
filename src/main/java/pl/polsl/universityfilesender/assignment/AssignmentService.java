package pl.polsl.universityfilesender.assignment;

import org.springframework.stereotype.Service;
import pl.polsl.universityfilesender.assignment.dto.AssignmentGetDto;

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
}
