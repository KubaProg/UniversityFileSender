package pl.polsl.universityfilesender.course.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDto {
    private Long id;
    private String courseName;
    private Long teacherId;

    public CourseDto() {
    }

    public CourseDto(Long id, String courseName, Long teacherId) {
        this.id = id;
        this.courseName = courseName;
        this.teacherId = teacherId;
    }


}
