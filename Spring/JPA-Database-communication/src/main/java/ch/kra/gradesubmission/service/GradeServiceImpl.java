package ch.kra.gradesubmission.service;

import java.util.List;

import ch.kra.gradesubmission.entity.Course;
import ch.kra.gradesubmission.entity.Grade;
import ch.kra.gradesubmission.entity.Student;
import ch.kra.gradesubmission.repository.CourseRepository;
import ch.kra.gradesubmission.repository.GradeRepository;
import ch.kra.gradesubmission.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Override
    public List<Grade> getAllGrades() {
        return (List<Grade>) gradeRepository.findAll();
    }

    @Override
    public Grade getGrade(final Long studentId, final Long courseId) {
        return gradeRepository.findByStudentIdAndCourseId(studentId, courseId);
    }

    @Override
    public Grade saveGrade(final Grade grade, final Long studentId, final Long courseId) {
        Student student = studentRepository.findById(studentId).get();
        grade.setStudent(student);

        Course course = courseRepository.findById(courseId).get();
        grade.setCourse(course);

        return gradeRepository.save(grade);
    }

    @Override
    public Grade updateGrade(final String score, final Long studentId, final Long courseId) {
        Grade grade = gradeRepository.findByStudentIdAndCourseId(studentId, courseId);
        grade.setScore(score);
        return gradeRepository.save(grade); //If we save a grade that is already present in the database, it will update it.
    }

    @Override
    public void deleteGrade(final Long studentId, final Long courseId) {
        
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
