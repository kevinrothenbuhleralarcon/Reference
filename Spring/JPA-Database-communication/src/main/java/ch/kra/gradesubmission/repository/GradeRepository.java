package ch.kra.gradesubmission.repository;

import ch.kra.gradesubmission.model.Grade;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GradeRepository extends CrudRepository<Grade, Long> {
    Optional<Grade> findByStudentIdAndCourseId(final Long studentId, final Long courseId);
    List<Grade> findAllByStudentId(final Long id);
    List<Grade> findAllByCourseId(final Long id);
    void deleteByStudentIdAndCourseId(final Long studentId, final Long courseId);
}