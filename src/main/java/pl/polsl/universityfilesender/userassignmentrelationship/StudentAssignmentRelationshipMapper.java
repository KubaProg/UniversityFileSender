package pl.polsl.universityfilesender.userassignmentrelationship;

import org.springframework.stereotype.Component;
import pl.polsl.universityfilesender.file.FileMapper;
import pl.polsl.universityfilesender.user.User;
import pl.polsl.universityfilesender.userassignmentrelationship.dto.StudentAssignmentRelationshipDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class StudentAssignmentRelationshipMapper {

    private final FileMapper fileMapper;

    public StudentAssignmentRelationshipMapper(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public StudentAssignmentRelationshipDto toDto(StudentAssignmentRelationship studentAssignmentRelationship) {
        StudentAssignmentRelationshipDto studentAssignmentRelationshipDto = new StudentAssignmentRelationshipDto();

        studentAssignmentRelationshipDto.setId(studentAssignmentRelationship.getId());

        User student = studentAssignmentRelationship.getStudent();

        if (student != null) {
            studentAssignmentRelationshipDto.setStudentId(student.getId());
            studentAssignmentRelationshipDto.setStudentFirstName(student.getFirstName());
            studentAssignmentRelationshipDto.setStudentLastName(student.getLastName());
        }

        studentAssignmentRelationshipDto.setFiles(fileMapper.toDtoList(new ArrayList<>(studentAssignmentRelationship.getFiles())));
        studentAssignmentRelationshipDto.setStatus(studentAssignmentRelationship.getStatus().toString());

        return studentAssignmentRelationshipDto;
    }


    public List<StudentAssignmentRelationshipDto> toDto(Set<StudentAssignmentRelationship> studentAssignmentRelationships) {
        return studentAssignmentRelationships.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
