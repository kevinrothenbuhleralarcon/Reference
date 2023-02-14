package ch.kra.gradesubmission.exception;

public class CourseNotFoundException extends RuntimeException { 

    public CourseNotFoundException(final Long id) {
        super("The course id '" + id + "' does not exist in our records");
    }
    
}