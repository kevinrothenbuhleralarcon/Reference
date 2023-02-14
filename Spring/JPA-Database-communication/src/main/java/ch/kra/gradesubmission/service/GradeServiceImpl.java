package ch.kra.gradesubmission.service;

import ch.kra.gradesubmission.exception.GradeNotFoundException;
import ch.kra.gradesubmission.model.Course;
import ch.kra.gradesubmission.model.Grade;
import ch.kra.gradesubmission.model.Student;
import ch.kra.gradesubmission.repository.GradeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final StudentService studentService;
    private final CourseService courseService;

    @Override
    public List<Grade> getAllGrades() {
        return (List<Grade>) gradeRepository.findAll();
    }

    @Override
    public Grade getGrade(final Long studentId, final Long courseId) {
        return gradeRepository.findByStudentIdAndCourseId(studentId, courseId)
                .orElseThrow(() -> new GradeNotFoundException(studentId, courseId));
    }

    @Override
    public Grade saveGrade(final Grade grade, final Long studentId, final Long courseId) {
        Student student = studentService.getStudent(studentId);
        grade.setStudent(student);

        Course course = courseService.getCourse(courseId);
        grade.setCourse(course);

        return gradeRepository.save(grade);
    }

    @Override
    public Grade updateGrade(final String score, final Long studentId, final Long courseId) {
        Grade grade = gradeRepository.findByStudentIdAndCourseId(studentId, courseId)
                .orElseThrow(() -> new GradeNotFoundException(studentId, courseId));
        grade.setScore(score);
        return gradeRepository.save(grade); //If we save a grade that is already present in the database, it will update it.
    }

    @Override
    public void deleteGrade(final Long studentId, final Long courseId) {
        gradeRepository.deleteByStudentIdAndCourseId(studentId, courseId);
    }

    @Override
    public List<Grade> getStudentGrades(final Long studentId) {
        return gradeRepository.findAllByStudentId(studentId);
    }

    @Override
    public List<Grade> getCourseGrades(final Long courseId) {
        return gradeRepository.findAllByCourseId(courseId);
    }

}
