package pl.polsl.universityfilesender.userassignmentrelationship;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentAssignmentRelationshipService {

    private final StudentAssignmentRelationshipRepository studentAssignmentRelationshipRepository;


    public StudentAssignmentRelationshipService(StudentAssignmentRelationshipRepository studentAssignmentRelationshipRepository) {
        this.studentAssignmentRelationshipRepository = studentAssignmentRelationshipRepository;
    }

    public List<StudentAssignmentRelationship> findAllByAssignment(Long assignmentId) {
        return studentAssignmentRelationshipRepository.findAllByAssignmentId(assignmentId);
    }

}
