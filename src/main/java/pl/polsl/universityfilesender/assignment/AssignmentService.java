package pl.polsl.universityfilesender.assignment;

import org.springframework.stereotype.Service;
import pl.polsl.universityfilesender.assignment.dto.AssignmentGetDto;
import pl.polsl.universityfilesender.assignment.dto.AssignmentSaveRequest;
import pl.polsl.universityfilesender.assignment.dto.DetailedAssignmentDto;
import pl.polsl.universityfilesender.assignment.dto.StudentAndAssignmentStatusDto;
import pl.polsl.universityfilesender.course.Course;
import pl.polsl.universityfilesender.course.CourseRepository;
import pl.polsl.universityfilesender.exception.EntityNotFoundException;
import pl.polsl.universityfilesender.userassignmentrelationship.StudentAssignmentRelationship;
import pl.polsl.universityfilesender.userassignmentrelationship.StudentAssignmentRelationshipService;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final AssignmentMapper assignmentMapper;

    private final StudentAssignmentRelationshipService studentAssignmentRelationshipService;

    private final CourseRepository courseRepository;

    public AssignmentService(AssignmentRepository assignmentRepository, AssignmentMapper assignmentMapper, StudentAssignmentRelationshipService studentAssignmentRelationshipService, CourseRepository courseRepository) {
        this.assignmentRepository = assignmentRepository;
        this.assignmentMapper = assignmentMapper;
        this.studentAssignmentRelationshipService = studentAssignmentRelationshipService;
        this.courseRepository = courseRepository;
    }

    @Transactional
    public void deleteAssignment(Long assignmentId) {
        assignmentRepository.deleteById(assignmentId);
    }

    public List<AssignmentGetDto> getAllAssignmentsByCourseId(Long courseId) {
        List<Assignment> assignments = assignmentRepository.findAllByCourseId(courseId);
        return assignmentMapper.toDtoList(assignments);
    }

    public DetailedAssignmentDto getDetailedAssignment(Long assignmentId) {
        Assignment assignment = getAssignmentById(assignmentId);
        return assignmentMapper.toDetailedDto(assignment);
    }

    public Assignment getAssignmentById(Long assignmentId) {
        return assignmentRepository.findById(assignmentId).orElseThrow(() -> new EntityNotFoundException(Assignment.class, "id", assignmentId.toString()));
    }

    public List<StudentAndAssignmentStatusDto> getStudentsWithAssignmentStatus(Long assignmentId) {
        return assignmentMapper.toDto(studentAssignmentRelationshipService.findAllByAssignment(assignmentId));
    }


    public AssignmentGetDto saveAssignment(Long courseId, AssignmentSaveRequest assignmentSaveRequest) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new EntityNotFoundException(Course.class, "id", courseId.toString()));


        Assignment assignment = new Assignment();
        assignment.setAssignmentName(assignmentSaveRequest.getAssignmentName());
        assignment.setDescription(assignmentSaveRequest.getDescription());
        assignment.setCourse(course);
        assignment.setDeadlineDate(assignmentSaveRequest.getDeadlineDate());

        Set<StudentAssignmentRelationship> studentAssignmentRelationships = new HashSet<>();
        course.getCourseEnrollments().forEach(courseEnrollment -> {
            StudentAssignmentRelationship studentAssignmentRelationship = new StudentAssignmentRelationship();
            studentAssignmentRelationship.setAssignment(assignment);
            studentAssignmentRelationship.setStudent(courseEnrollment.getStudent());
            studentAssignmentRelationship.setStatus(StudentAssignmentRelationship.Status.PENDING);
            studentAssignmentRelationships.add(studentAssignmentRelationship);
        });

        assignment.setUserAssignmentRelationships(studentAssignmentRelationships);


        // Add the assignment to the course
        course.getAssignments().add(assignment);

        // Save the assignment
        return assignmentMapper.toDto(assignmentRepository.save(assignment));
    }
}
