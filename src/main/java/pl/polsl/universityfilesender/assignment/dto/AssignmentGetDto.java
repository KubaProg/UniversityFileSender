package pl.polsl.universityfilesender.assignment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignmentGetDto {
    private Long id;

    private String assignmentName;

    private String description;

    private String deadlineDate;
}
