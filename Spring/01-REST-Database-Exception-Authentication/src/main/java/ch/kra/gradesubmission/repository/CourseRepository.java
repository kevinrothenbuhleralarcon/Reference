package ch.kra.gradesubmission.repository;

import ch.kra.gradesubmission.model.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {
}