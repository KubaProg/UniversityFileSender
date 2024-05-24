package pl.polsl.universityfilesender.assignment;

import org.springframework.stereotype.Component;
import pl.polsl.universityfilesender.assignment.dto.AssignmentGetDto;
import pl.polsl.universityfilesender.assignment.dto.DetailedAssignmentDto;
import pl.polsl.universityfilesender.userassignmentrelationship.StudentAssignmentRelationshipMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssignmentMapper {

    private final StudentAssignmentRelationshipMapper studentAssignmentRelationshipMapper;

    public AssignmentMapper(StudentAssignmentRelationshipMapper studentAssignmentRelationshipMapper) {
        this.studentAssignmentRelationshipMapper = studentAssignmentRelationshipMapper;
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
}
