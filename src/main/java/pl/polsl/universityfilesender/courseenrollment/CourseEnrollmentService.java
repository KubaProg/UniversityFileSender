package pl.polsl.universityfilesender.courseenrollment;

import org.springframework.stereotype.Service;
import pl.polsl.universityfilesender.course.Course;
import pl.polsl.universityfilesender.course.CourseRepository;
import pl.polsl.universityfilesender.courseenrollment.dto.CourseEnrollmentDetailsDto;
import pl.polsl.universityfilesender.exception.EntityNotFoundException;
import pl.polsl.universityfilesender.user.User;
import pl.polsl.universityfilesender.userassignmentrelationship.StudentAssignmentRelationship;
import pl.polsl.universityfilesender.userassignmentrelationship.StudentAssignmentRelationshipRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseEnrollmentService {

    private final CourseEnrollmentRepository courseEnrollmentRepository;
    private final CourseEnrollmentMapper courseEnrollmentMapper;

    private final CourseRepository courseRepository;


    private final StudentAssignmentRelationshipRepository studentAssignmentRelationshipRepository;

    public CourseEnrollmentService(CourseEnrollmentRepository courseEnrollmentRepository, CourseEnrollmentMapper courseEnrollmentMapper, CourseRepository courseRepository, StudentAssignmentRelationshipRepository studentAssignmentRelationshipRepository) {
        this.courseEnrollmentRepository = courseEnrollmentRepository;
        this.courseEnrollmentMapper = courseEnrollmentMapper;
        this.courseRepository = courseRepository;
        this.studentAssignmentRelationshipRepository = studentAssignmentRelationshipRepository;
    }


    public List<CourseEnrollmentDetailsDto> getPendingEnrollmentsForCourse(Long courseId) {
        return courseEnrollmentMapper.toDetailsDto(courseEnrollmentRepository.findAllByStatusAndCourseId(CourseEnrollment.Status.PENDING, courseId));
    }

    @Transactional
    public void acceptEnrollment(Long enrollmentId) {
        CourseEnrollment courseEnrollment = courseEnrollmentRepository
                .findById(enrollmentId)
                .orElseThrow(() -> new EntityNotFoundException(CourseEnrollment.class, "id", String.valueOf(enrollmentId)));

        courseEnrollment.setStatus(CourseEnrollment.Status.ACCEPTED);

        // create student-assignment relationships for all assignments in the course
        Course course = courseEnrollment.getCourse();
        List<StudentAssignmentRelationship> studentAssignmentRelationships = course.getAssignments().stream()
                .map(assignment -> StudentAssignmentRelationship.builder()
                        .student(courseEnrollment.getStudent())
                        .assignment(assignment)
                        .status(StudentAssignmentRelationship.Status.NOT_SUBMITTED)
                        .build())
                        .collect(Collectors.toList());

        // save all student-assignment relationships
        studentAssignmentRelationshipRepository.saveAll(studentAssignmentRelationships);

        courseEnrollmentRepository.save(courseEnrollment);
    }

    @Transactional
    public CourseEnrollmentDetailsDto createPendingEnrollment(Long courseId, User student) {
        if (courseEnrollmentRepository.existsByCourseIdAndStudentId(courseId, student.getId())) {
            throw new IllegalArgumentException("Student has already enrolled to this course.");
        }

        Course course = courseRepository
                .findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException(Course.class, "id", String.valueOf(courseId)));

        CourseEnrollment courseEnrollmentToSave = CourseEnrollment.builder()
                .student(student)
                .course(course)
                .status(CourseEnrollment.Status.PENDING)
                .build();

        CourseEnrollment savedCourseEnrollment = courseEnrollmentRepository.save(courseEnrollmentToSave);


        return courseEnrollmentMapper.toDetailsDto(savedCourseEnrollment);
    }
}
