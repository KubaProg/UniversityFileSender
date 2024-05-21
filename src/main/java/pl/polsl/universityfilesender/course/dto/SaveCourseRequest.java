package pl.polsl.universityfilesender.course.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveCourseRequest {

    @NotBlank(message = "Course name is required")
    @Size(min = 1, max = 100, message = "Course name must be between 1 and 100 characters")
    private String courseName;
}
