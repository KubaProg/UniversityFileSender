package pl.polsl.universityfilesender.file;

import org.springframework.stereotype.Service;
import pl.polsl.universityfilesender.assignment.Assignment;
import pl.polsl.universityfilesender.assignment.AssignmentRepository;
import pl.polsl.universityfilesender.exception.EntityNotFoundException;
import pl.polsl.universityfilesender.file.dto.FileDto;
import pl.polsl.universityfilesender.user.User;
import pl.polsl.universityfilesender.user.UserRepository;
import pl.polsl.universityfilesender.userassignmentrelationship.StudentAssignmentRelationship;
import pl.polsl.universityfilesender.userassignmentrelationship.StudentAssignmentRelationshipRepository;

import java.util.List;

@Service
public class FileService {

    private final StudentAssignmentRelationshipRepository studentAssignmentRelationshipRepository;
    private final AssignmentRepository assignmentRepository;
    private final UserRepository userRepository;
    private final FileMapper fileMapper;

    public FileService(StudentAssignmentRelationshipRepository studentAssignmentRelationshipRepository, AssignmentRepository assignmentRepository, UserRepository userRepository, FileMapper fileMapper) {
        this.studentAssignmentRelationshipRepository = studentAssignmentRelationshipRepository;
        this.assignmentRepository = assignmentRepository;
        this.userRepository = userRepository;
        this.fileMapper = fileMapper;
    }

    public List<FileDto> downloadAssignmentFiles(Long studentId, Long assignmentId) {
        Assignment assignment = assignmentRepository.findById(assignmentId).orElseThrow(() -> new EntityNotFoundException(Assignment.class, "id", assignmentId.toString()));
        User student = userRepository.findById(studentId).orElseThrow(() -> new EntityNotFoundException(User.class, "id", studentId.toString()));

        StudentAssignmentRelationship studentAssignmentRelationship = studentAssignmentRelationshipRepository.findByStudentAndAssignment(student, assignment);

        if (studentAssignmentRelationship == null) {
            throw new EntityNotFoundException(StudentAssignmentRelationship.class, "studentId", studentId.toString());
        }

        return fileMapper.toDto(studentAssignmentRelationship.getFiles());
    }

}
