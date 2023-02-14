package ch.kra.gradesubmission.service;

import java.util.List;

import ch.kra.gradesubmission.entity.Course;
import ch.kra.gradesubmission.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    @Override
    public List<Course> getCourses() {
        return (List<Course>) courseRepository.findAll();
    }
    private final CourseRepository courseRepository;

    @Override
    public Course getCourse(Long id) {
        return courseRepository.findById(id).get();
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
