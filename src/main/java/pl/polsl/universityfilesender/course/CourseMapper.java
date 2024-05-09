package pl.polsl.universityfilesender.course;

import org.springframework.stereotype.Component;
import pl.polsl.universityfilesender.course.dto.CourseDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMapper {
    public CourseDto toDto(Course course) {
        return new CourseDto(course.getId(), course.getCourseName(), course.getTeacher().getId());
    }

    // to dto list
    public List<CourseDto> toDtoList(List<Course> courses) {
        return courses.stream().map(this::toDto).collect(Collectors.toList());
    }

}
