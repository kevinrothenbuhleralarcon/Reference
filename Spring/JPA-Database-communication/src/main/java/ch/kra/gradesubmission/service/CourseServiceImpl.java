package ch.kra.gradesubmission.service;

import java.util.List;

import ch.kra.gradesubmission.exception.CourseNotFoundException;
import ch.kra.gradesubmission.model.Course;
import ch.kra.gradesubmission.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Override
    public List<Course> getCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Course getCourse(final Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
    }

    @Override
    public Course saveCourse(final Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(final Long id) {
        courseRepository.deleteById(id);
    }
}
