package pl.polsl.universityfilesender.userassignmentrelationship;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentAssignmentRelationshipRepository extends JpaRepository<StudentAssignmentRelationship, Long> {

    List<StudentAssignmentRelationship> findAllByAssignmentId(Long assignment);
}
