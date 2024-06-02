package pl.polsl.universityfilesender.userassignmentrelationship;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import pl.polsl.universityfilesender.assignment.Assignment;
import pl.polsl.universityfilesender.assignment.AssignmentRepository;
import pl.polsl.universityfilesender.assignment.dto.SubmitAssignmentRequest;
import pl.polsl.universityfilesender.exception.EntityNotFoundException;
import pl.polsl.universityfilesender.file.File;
import pl.polsl.universityfilesender.user.User;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentAssignmentRelationshipService {

    private final StudentAssignmentRelationshipRepository studentAssignmentRelationshipRepository;

    private final AssignmentRepository assignmentRepository;


    public StudentAssignmentRelationshipService(StudentAssignmentRelationshipRepository studentAssignmentRelationshipRepository, AssignmentRepository assignmentRepository) {
        this.studentAssignmentRelationshipRepository = studentAssignmentRelationshipRepository;
        this.assignmentRepository = assignmentRepository;
    }

    public List<StudentAssignmentRelationship> findAllByAssignment(Long assignmentId) {
        return studentAssignmentRelationshipRepository.findAllByAssignmentId(assignmentId);
    }

    @Transactional
    public void submitAssignment(User student, SubmitAssignmentRequest submitAssignmentRequest, Long assignmentId) {

        Assignment assignment = assignmentRepository.findById(assignmentId).orElseThrow(() -> new EntityNotFoundException(Assignment.class, "id", assignmentId.toString()));

        StudentAssignmentRelationship attachment = studentAssignmentRelationshipRepository.findByStudentAndAssignment(student, assignment).orElseThrow(
                () -> new EntityNotFoundException(StudentAssignmentRelationship.class, "studentId", student.getId().toString(), "assignmentId", assignmentId.toString()));


        // remove old files
        attachment.getFiles().clear();

        if(submitAssignmentRequest.getFiles() != null){
            attachment.setStatus(StudentAssignmentRelationship.Status.SUBMITTED);

            // map dto files to entity files
            List<File> files = submitAssignmentRequest.getFiles().stream().map(file -> {
                File attachmentFile = new File();
                attachmentFile.setFileName(file.getOriginalFilename());
                attachmentFile.setFileType(file.getContentType());
                attachmentFile.setStudentAssignmentRelationship(attachment);

                try {
                    attachmentFile.setData(file.getBytes());
                } catch (IOException e) {
                    throw new RuntimeException("Error while setting file data");
                }
                return attachmentFile;
            }).collect(Collectors.toList());

            // add files to attachment
            attachment.getFiles().addAll(files);

        } else {
            attachment.setStatus(StudentAssignmentRelationship.Status.NOT_SUBMITTED);
        }


        // save attachment
        try {
            studentAssignmentRelationshipRepository.save(attachment);
        } catch (DataAccessException e) {
            throw new RuntimeException("Error while saving attachment to database");
        }
    }
}
