package ch.kra.gradesubmission.exception;

public class CourseNotFoundException extends RuntimeException {
    // Throw it and use ControllerAdvice and ExceptionHandler to act accordingly, can create an errorResponse class to add a content to the responseEntity

    public CourseNotFoundException(final Long id) { //constructor gets called when exception is thrown
        super("The course id '" + id + "' does not exist in our records"); //passing an error message into the parent constructor allows us to access it later.
    }
    
}