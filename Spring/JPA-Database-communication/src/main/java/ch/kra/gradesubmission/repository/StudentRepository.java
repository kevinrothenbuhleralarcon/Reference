package ch.kra.gradesubmission.repository;

import ch.kra.gradesubmission.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {

}