package pl.polsl.universityfilesender.courseenrollment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseEnrollmentDetailsDto {

    private Long enrollmentId;

    private String userFirstName;

    private String userLastName;

    private Long userId;

    private Long courseId;

    private String status;
}
