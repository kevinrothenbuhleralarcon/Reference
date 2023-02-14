package ch.kra.gradesubmission.repository;

import ch.kra.gradesubmission.entity.Grade;
import org.springframework.data.repository.CrudRepository;

public interface GradeRepository extends CrudRepository<Grade, Long> {
    Grade findByStudentId(final Long id);
}