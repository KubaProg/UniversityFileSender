package pl.polsl.universityfilesender.userassignmentrelationship;

import org.springframework.stereotype.Service;
import pl.polsl.universityfilesender.userassignmentrelationship.dto.StudentAssignmentRelationshipDto;

import java.util.List;

@Service
public class StudentAssignmentRelationshipService {

    private final StudentAssignmentRelationshipRepository studentAssignmentRelationshipRepository;


    public StudentAssignmentRelationshipService(StudentAssignmentRelationshipRepository studentAssignmentRelationshipRepository) {
        this.studentAssignmentRelationshipRepository = studentAssignmentRelationshipRepository;
    }

}
