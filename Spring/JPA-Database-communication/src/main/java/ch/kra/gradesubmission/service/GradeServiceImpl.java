package ch.kra.gradesubmission.service;

import java.util.List;

import ch.kra.gradesubmission.entity.Grade;
import ch.kra.gradesubmission.entity.Student;
import ch.kra.gradesubmission.repository.GradeRepository;
import ch.kra.gradesubmission.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    
    @Override
    public Grade getGrade(final Long studentId, final Long courseId) {
        return gradeRepository.findByStudentId(studentId);
    }

    @Override
    public Grade saveGrade(final Grade grade, final Long studentId, final Long courseId) {
        Student student = studentRepository.findById(studentId).get();
        grade.setStudent(student);
        return gradeRepository.save(grade);
    }

    @Override
    public Grade updateGrade(final String score, final Long studentId, final Long courseId) {
        return null;
    }

    @Override
    public void deleteGrade(final Long studentId, final Long courseId) {
        
    }

    @Override
    public List<Grade> getStudentGrades(final Long studentId) {
        return null;
    }

    @Override
    public List<Grade> getCourseGrades(Long courseId) {
        return null;
    }

    @Override
    public List<Grade> getAllGrades() {
        return null;
    }

}
