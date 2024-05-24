package pl.polsl.universityfilesender.assignment.dto;


import lombok.Getter;
import lombok.Setter;
import pl.polsl.universityfilesender.userassignmentrelationship.dto.StudentAssignmentRelationshipDto;

import java.util.List;

@Getter
@Setter
public class DetailedAssignmentDto {

    private Long id;

    private String assignmentName;

    private String description;

    private String deadlineDate;

    private List<StudentAssignmentRelationshipDto> studentAssignmentRelationships;
}
