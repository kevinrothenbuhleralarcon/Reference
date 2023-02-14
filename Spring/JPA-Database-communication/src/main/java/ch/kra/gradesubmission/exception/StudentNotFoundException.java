package ch.kra.gradesubmission.exception;

public class StudentNotFoundException extends RuntimeException {
    // Throw it and use ControllerAdvice and ExceptionHandler to act accordingly, can create an errorResponse class to add a content to the responseEntity
    public StudentNotFoundException(final Long id) {
        super("The student id '" + id + "' does not exist in our records"); //passing an error message into the parent constructor allows us to access it later.
    }
    
}