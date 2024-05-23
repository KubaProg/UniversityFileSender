package pl.polsl.universityfilesender.assignment;

import org.springframework.stereotype.Component;
import pl.polsl.universityfilesender.assignment.dto.AssignmentGetDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssignmentMapper {

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
}