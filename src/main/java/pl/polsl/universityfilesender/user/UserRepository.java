package pl.polsl.universityfilesender.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.polsl.universityfilesender.assignment.Assignment;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);


    @Query("SELECT u FROM User u JOIN StudentAssignmentRelationship  sar ON u.id = sar.student.id WHERE sar.assignment = :assignment")
    List<User> findAllByAssignments(Assignment assignment);

    @Query("SELECT u FROM User u JOIN Course c ON u.id = c.teacher.id JOIN Assignment a ON c.id = a.course.id WHERE a = :assignment")
    Optional<User> findTeacherByAssignment(Assignment assignment);

    @Query("SELECT u FROM User u JOIN StudentAssignmentRelationship sar ON u.id = sar.student.id WHERE sar.assignment = :assignment")
    Optional<User> findStudentByAssignment(Assignment assignment);

}
