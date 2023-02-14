package ch.kra.gradesubmission.repository;

import ch.kra.gradesubmission.entity.Grade;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GradeRepository extends CrudRepository<Grade, Long> {
    Grade findByStudentIdAndCourseId(final Long studentId, final Long courseId);
    List<Grade> findAllByStudentId(final Long id);
    List<Grade> findAllByCourseId(final Long id);
}