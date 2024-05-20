package pl.polsl.universityfilesender.course;

import org.springframework.stereotype.Service;
import pl.polsl.universityfilesender.course.dto.CourseDto;
import pl.polsl.universityfilesender.exception.EntityNotFoundException;
import pl.polsl.universityfilesender.user.User;

import java.util.List;

@Service
public class CourseService {

    CourseRepository courseRepository;
    CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }


    public List<CourseDto> getCoursesByTeacher(User teacher) {

        if (teacher == null) {
            throw new EntityNotFoundException(User.class, "id", "null");
        }

        List<Course> courses = courseRepository.findAllByTeacher(teacher);
        return courseMapper.toDtoList(courses);
    }
}
