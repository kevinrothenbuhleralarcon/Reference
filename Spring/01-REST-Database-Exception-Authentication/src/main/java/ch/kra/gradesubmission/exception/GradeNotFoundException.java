package ch.kra.gradesubmission.exception;

public class GradeNotFoundException extends RuntimeException {
    // Throw it and use ControllerAdvice and ExceptionHandler to act accordingly, can create an errorResponse class to add a content to the responseEntity
    public GradeNotFoundException(final Long studentId, final Long courseId) {
        super("The grade with student id: '" + studentId + "' and course id: '" + courseId + "' does not exist in our records"); //passing an error message into the parent constructor allows us to access it later.
    }
    
}