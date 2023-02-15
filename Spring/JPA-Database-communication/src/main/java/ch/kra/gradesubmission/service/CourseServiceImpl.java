package ch.kra.gradesubmission.service;

import java.util.List;
import java.util.Set;

import ch.kra.gradesubmission.exception.CourseNotFoundException;
import ch.kra.gradesubmission.model.Course;
import ch.kra.gradesubmission.model.Student;
import ch.kra.gradesubmission.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final StudentService studentService;

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
        try {
            courseRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            // Not needed just to test what happens if we catch a runtimeException, in this case we need to rethrow it if we want to handle it in the Exception Handler
            System.out.println("Test try-catch: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Course addStudentToCourse(Long studentId, Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseNotFoundException(courseId));
        Student student = studentService.getStudent(studentId);

        course.getStudents().add(student);
        return courseRepository.save(course);
    }

    @Override
    public Set<Student> getEnrolledStudents(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));
        return course.getStudents();
    }
}
