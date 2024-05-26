package pl.polsl.universityfilesender.assignment.dto;

import lombok.Getter;
import lombok.Setter;
import pl.polsl.universityfilesender.user.dto.UserDto;

@Getter
@Setter
public class StudentAndAssignmentStatusDto {
    private UserDto student;
    private String status;
}
