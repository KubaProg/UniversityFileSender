package pl.polsl.universityfilesender.file;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import pl.polsl.universityfilesender.assignment.Assignment;
import pl.polsl.universityfilesender.assignment.AssignmentRepository;
import pl.polsl.universityfilesender.exception.EntityNotFoundException;
import pl.polsl.universityfilesender.user.User;
import pl.polsl.universityfilesender.user.UserRepository;
import pl.polsl.universityfilesender.userassignmentrelationship.StudentAssignmentRelationship;
import pl.polsl.universityfilesender.userassignmentrelationship.StudentAssignmentRelationshipRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

    public ByteArrayResource downloadAssignmentFiles(Long studentId, Long assignmentId) {
        Assignment assignment = assignmentRepository.findById(assignmentId).orElseThrow(() -> new EntityNotFoundException(Assignment.class, "id", assignmentId.toString()));
        User student = userRepository.findById(studentId).orElseThrow(() -> new EntityNotFoundException(User.class, "id", studentId.toString()));

        StudentAssignmentRelationship studentAssignmentRelationship = studentAssignmentRelationshipRepository.findByStudentAndAssignment(student, assignment)
                .orElseThrow(() -> new EntityNotFoundException(StudentAssignmentRelationship.class, "studentId", studentId.toString()));

        Set<File> files = studentAssignmentRelationship.getFiles();

        if(files.isEmpty()) {
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
