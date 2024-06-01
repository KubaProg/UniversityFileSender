package pl.polsl.universityfilesender.courseenrollment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollment, Long> {


    List<CourseEnrollment> findAllByStatusAndCourseId(CourseEnrollment.Status status, Long courseId);
}