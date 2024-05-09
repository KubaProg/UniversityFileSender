package pl.polsl.universityfilesender.course.dto;

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

    public Long getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public Long getTeacherId() {
        return teacherId;
    }
}
