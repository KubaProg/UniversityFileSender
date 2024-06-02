package pl.polsl.universityfilesender.assignment;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.polsl.universityfilesender.assignment.dto.AssignmentGetDto;
import pl.polsl.universityfilesender.assignment.dto.AssignmentSaveRequest;
import pl.polsl.universityfilesender.assignment.dto.DetailedAssignmentDto;
import pl.polsl.universityfilesender.assignment.dto.StudentAndAssignmentStatusDto;
import pl.polsl.universityfilesender.course.Course;
import pl.polsl.universityfilesender.course.CourseRepository;
import pl.polsl.universityfilesender.exception.EntityNotFoundException;
import pl.polsl.universityfilesender.file.File;
import pl.polsl.universityfilesender.userassignmentrelationship.StudentAssignmentRelationship;
import pl.polsl.universityfilesender.userassignmentrelationship.StudentAssignmentRelationshipService;

import javax.transaction.Transactional;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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


    @Transactional
    public AssignmentGetDto saveAssignment(Long courseId, AssignmentSaveRequest assignmentSaveRequest) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new EntityNotFoundException(Course.class, "id", courseId.toString()));


        Assignment assignment = new Assignment();
        assignment.setAssignmentName(assignmentSaveRequest.getAssignmentName());
        assignment.setDescription(assignmentSaveRequest.getDescription());
        assignment.setCourse(course);
        assignment.setDeadlineDate(assignmentSaveRequest.getDeadlineDate());

            Set<File> files = assignmentSaveRequest.getFiles().stream().map(file -> {
                File fileEntity = new File();
                fileEntity.setFileName(file.getOriginalFilename());
                fileEntity.setAssignment(assignment);
                fileEntity.setFileType(file.getContentType());
                try {
                    fileEntity.setData(file.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return fileEntity;
            }).collect(Collectors.toSet());

            assignment.setFiles(files);



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

    @Transactional
    public void updateAssignment(Long assignmentId, AssignmentSaveRequest assignmentSaveRequest) {
        Assignment assignment = getAssignmentById(assignmentId);
        assignment.setAssignmentName(assignmentSaveRequest.getAssignmentName());
        assignment.setDescription(assignmentSaveRequest.getDescription());
        assignment.setDeadlineDate(assignmentSaveRequest.getDeadlineDate());
        assignment.getFiles().clear();

        if (assignmentSaveRequest.getFiles() != null) {
            assignment.getFiles().addAll(assignmentSaveRequest.getFiles().stream().map(file -> {
                File fileEntity = new File();
                fileEntity.setFileName(file.getOriginalFilename());
                fileEntity.setAssignment(assignment);
                try {
                    fileEntity.setData(file.getBytes());
                    fileEntity.setFileName(file.getOriginalFilename());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return fileEntity;
            }).collect(Collectors.toSet()));
        }


        assignmentRepository.save(assignment);
    }

    public ByteArrayResource downloadAssignment(Long assignmentId) {
        Assignment assignment = getAssignmentById(assignmentId);
        Set<File> files = assignment.getFiles();

        if (files.isEmpty()) {
            throw new EntityNotFoundException(File.class, "assignmentId", assignmentId.toString());
        }

        return createZipResource(files);
    }


    private ByteArrayResource createZipResource(Set<File> files) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream)) {

            for (File file : files) {
                ZipEntry zipEntry = new ZipEntry(file.getFileName());
                zipOutputStream.putNextEntry(zipEntry);
                zipOutputStream.write(file.getData());
                zipOutputStream.closeEntry();
            }

            zipOutputStream.finish();
            return new ByteArrayResource(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating ZIP file", e);
        }
    }
}
