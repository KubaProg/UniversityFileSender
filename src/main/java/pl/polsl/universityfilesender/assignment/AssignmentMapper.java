package pl.polsl.universityfilesender.assignment;

import org.springframework.stereotype.Component;
import pl.polsl.universityfilesender.assignment.dto.AssignmentGetDto;
import pl.polsl.universityfilesender.assignment.dto.DetailedAssignmentDto;
import pl.polsl.universityfilesender.assignment.dto.StudentAndAssignmentStatusDto;
import pl.polsl.universityfilesender.user.UserMapper;
import pl.polsl.universityfilesender.userassignmentrelationship.StudentAssignmentRelationship;
import pl.polsl.universityfilesender.userassignmentrelationship.StudentAssignmentRelationshipMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssignmentMapper {

    private final StudentAssignmentRelationshipMapper studentAssignmentRelationshipMapper;

    private final UserMapper userMapper;

    public AssignmentMapper(StudentAssignmentRelationshipMapper studentAssignmentRelationshipMapper, UserMapper userMapper) {
        this.studentAssignmentRelationshipMapper = studentAssignmentRelationshipMapper;
        this.userMapper = userMapper;
    }


    public AssignmentGetDto toDto(Assignment assignment) {
        AssignmentGetDto dto = new AssignmentGetDto();
        dto.setId(assignment.getId());
        dto.setAssignmentName(assignment.getAssignmentName());
        dto.setDescription(assignment.getDescription());
        dto.setDeadlineDate(assignment.getDeadlineDate().toString());
        return dto;
    }

    public List<AssignmentGetDto> toDtoList(List<Assignment> assignments) {
        return assignments.stream().map(this::toDto).collect(Collectors.toList());
    }


    public DetailedAssignmentDto toDetailedDto(Assignment assignment) {
        DetailedAssignmentDto dto = new DetailedAssignmentDto();
        dto.setId(assignment.getId());
        dto.setAssignmentName(assignment.getAssignmentName());
        dto.setDescription(assignment.getDescription());
        dto.setDeadlineDate(assignment.getDeadlineDate().toString());
        dto.setStudentAssignmentRelationships(studentAssignmentRelationshipMapper.toDto(assignment.getUserAssignmentRelationships()));
        return dto;
    }

    public StudentAndAssignmentStatusDto toDto(StudentAssignmentRelationship studentAssignmentRelationship) {
        StudentAndAssignmentStatusDto dto = new StudentAndAssignmentStatusDto();
        dto.setStudent(userMapper.toUserDto(studentAssignmentRelationship.getStudent()));
        dto.setStatus(studentAssignmentRelationship.getStatus().toString());
        return dto;
    }

    public List<StudentAndAssignmentStatusDto> toDto(List<StudentAssignmentRelationship> studentAssignmentRelationships) {
        return studentAssignmentRelationships.stream().map(this::toDto).collect(Collectors.toList());
    }
}
