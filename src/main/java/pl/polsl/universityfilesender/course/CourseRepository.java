package pl.polsl.universityfilesender.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.polsl.universityfilesender.user.User;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByTeacher(User teacher);


    @Query("SELECT c FROM Course c JOIN c.courseEnrollments ce WHERE ce.student.id = :studentId AND ce.status = 'ACCEPTED'")
    List<Course> findAllByStudentId(@Param("studentId") Long studentId);

}
