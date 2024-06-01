package pl.polsl.universityfilesender.courseenrollment;

import org.springframework.stereotype.Component;
import pl.polsl.universityfilesender.courseenrollment.dto.CourseEnrollmentDetailsDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseEnrollmentMapper {
    public CourseEnrollmentDetailsDto toDetailsDto(CourseEnrollment courseEnrollment) {

            if (courseEnrollment == null) {
                return null;
            }

            CourseEnrollmentDetailsDto courseEnrollmentDetailsDto = new CourseEnrollmentDetailsDto();

            courseEnrollmentDetailsDto.setEnrollmentId(courseEnrollment.getId());
            courseEnrollmentDetailsDto.setUserFirstName(courseEnrollment.getStudent().getFirstName());
            courseEnrollmentDetailsDto.setUserLastName(courseEnrollment.getStudent().getLastName());
            courseEnrollmentDetailsDto.setUserId(courseEnrollment.getStudent().getId());
            courseEnrollmentDetailsDto.setCourseId(courseEnrollment.getCourse().getId());
            courseEnrollmentDetailsDto.setStatus(courseEnrollment.getStatus().name());

            return courseEnrollmentDetailsDto;
    }

    public List<CourseEnrollmentDetailsDto> toDetailsDto(List<CourseEnrollment> courseEnrollments) {
        return courseEnrollments.stream()
                .map(this::toDetailsDto)
                .collect(Collectors.toList());
    }



}
