package ch.kra.gradesubmission.service;

import ch.kra.gradesubmission.exception.EntityNotFoundException;
import ch.kra.gradesubmission.model.Course;
import ch.kra.gradesubmission.model.Student;
import ch.kra.gradesubmission.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<Student> getStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    @Override
    public Student getStudent(final Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Student.class));
    }

    @Override
    public Set<Course> getEnrolledCourses(Long id) {
        Student student = getStudent(id);
        return student.getCourses();
    }

    @Override
    public Student saveStudent(final Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(final Long id) {
        studentRepository.deleteById(id);
    }
}
