package ch.kra.gradesubmission.service;

import java.util.List;
import java.util.Set;

import ch.kra.gradesubmission.model.Course;
import ch.kra.gradesubmission.model.Student;

public interface StudentService {
    Student getStudent(Long id);
    Student saveStudent(Student student);
    void deleteStudent(Long id);
    List<Student> getStudents();
    Set<Course> getEnrolledCourses(Long id);
}