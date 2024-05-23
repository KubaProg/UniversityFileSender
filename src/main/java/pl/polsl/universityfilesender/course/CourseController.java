package pl.polsl.universityfilesender.course;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @DeleteMapping("/{courseId}")
    @PreAuthorize("@courseService.isCourseOwner(authentication, #courseId)")
    public ResponseEntity<Void> deleteCourse(@PathVariable("courseId") Long courseId) {
        courseService.deleteCourse(courseId);
        return ResponseEntity.noContent().build();

    }

}
