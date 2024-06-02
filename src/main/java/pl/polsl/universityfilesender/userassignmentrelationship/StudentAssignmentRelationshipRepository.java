package pl.polsl.universityfilesender.userassignmentrelationship;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.polsl.universityfilesender.assignment.Assignment;
import pl.polsl.universityfilesender.user.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentAssignmentRelationshipRepository extends JpaRepository<StudentAssignmentRelationship, Long> {

    List<StudentAssignmentRelationship> findAllByAssignmentId(Long assignment);

    @Query("SELECT sar FROM StudentAssignmentRelationship sar WHERE sar.student = :student AND sar.assignment = :assignment")
    Optional<StudentAssignmentRelationship> findByStudentAndAssignment(User student, Assignment assignment);
}
