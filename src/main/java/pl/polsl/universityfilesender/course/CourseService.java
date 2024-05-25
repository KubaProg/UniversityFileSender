package pl.polsl.universityfilesender.course;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.polsl.universityfilesender.course.dto.CourseDto;
import pl.polsl.universityfilesender.course.dto.SaveCourseRequest;
import pl.polsl.universityfilesender.exception.EntityNotFoundException;
import pl.polsl.universityfilesender.user.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CourseService {

    CourseRepository courseRepository;
    CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }


    public List<CourseDto> getCoursesByUser(User user) {

        if (user == null) {
            throw new EntityNotFoundException(User.class, "id", "null");
        }

        // If the user is a teacher, return all courses taught by the teacher
        if (user.getRole().equals(User.Role.ROLE_TEACHER)) {
            return courseMapper.toDtoList(courseRepository.findAllByTeacher(user));
        }

        // If the user is a student, return all courses enrolled by the student
        if (user.getRole().equals(User.Role.ROLE_STUDENT)) {
            return courseMapper.toDtoList(courseRepository.findAllByStudentId(user.getId()));
        }

        return null;

    }

    public CourseDto saveCourse(User user, SaveCourseRequest saveCourseRequest) {
        Course course = new Course();
        course.setTeacher(user);
        course.setCourseName(saveCourseRequest.getCourseName());
        courseRepository.save(course);
        return courseMapper.toDto(course);
    }

    public boolean isCourseOwner(Authentication authentication , Long courseId) {
        User currentUser = (User) authentication.getPrincipal();
        Course course = getCourse(courseId);

        return currentUser.getId().equals(course.getTeacher().getId());
    }

    @Transactional
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    private Course getCourse(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(() -> new EntityNotFoundException(Course.class, "id", String.valueOf(courseId)));
    }
}
