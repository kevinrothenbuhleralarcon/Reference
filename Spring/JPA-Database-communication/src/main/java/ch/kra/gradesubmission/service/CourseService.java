package ch.kra.gradesubmission.service;

import java.util.List;

import ch.kra.gradesubmission.model.Course;

public interface CourseService {
    Course getCourse(Long id);
    Course saveCourse(Course course);
    void deleteCourse(Long id);
    List<Course> getCourses();
}