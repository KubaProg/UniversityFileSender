package pl.polsl.universityfilesender.courseenrollment;

import org.springframework.stereotype.Service;
import pl.polsl.universityfilesender.courseenrollment.dto.CourseEnrollmentDetailsDto;

import java.util.List;

@Service
public class CourseEnrollmentService {

    private final CourseEnrollmentRepository courseEnrollmentRepository;
    private final CourseEnrollmentMapper courseEnrollmentMapper;

    public CourseEnrollmentService(CourseEnrollmentRepository courseEnrollmentRepository, CourseEnrollmentMapper courseEnrollmentMapper) {
        this.courseEnrollmentRepository = courseEnrollmentRepository;
        this.courseEnrollmentMapper = courseEnrollmentMapper;
    }


    public List<CourseEnrollmentDetailsDto> getPendingEnrollmentsForCourse(Long courseId) {
        return courseEnrollmentMapper.toDetailsDto(courseEnrollmentRepository.findAllByStatusAndCourseId(CourseEnrollment.Status.PENDING, courseId));
    }
}
