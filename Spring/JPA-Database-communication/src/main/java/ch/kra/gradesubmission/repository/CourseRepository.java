package ch.kra.gradesubmission.repository;

import ch.kra.gradesubmission.entity.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {
}