package pl.polsl.universityfilesender.userassignmentrelationship;

import org.springframework.stereotype.Service;

@Service
public class StudentAssignmentRelationshipService {

    private final StudentAssignmentRelationshipRepository studentAssignmentRelationshipRepository;


    public StudentAssignmentRelationshipService(StudentAssignmentRelationshipRepository studentAssignmentRelationshipRepository) {
        this.studentAssignmentRelationshipRepository = studentAssignmentRelationshipRepository;
    }

}
