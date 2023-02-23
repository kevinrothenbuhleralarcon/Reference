package ch.kra.gradesubmission.exception;

public class EntityNotFoundException extends RuntimeException {
    // Throw it and use ControllerAdvice and ExceptionHandler to act accordingly, can create an errorResponse class to add a content to the responseEntity

    public EntityNotFoundException(Long id, Class<?> entity) { //constructor gets called when exception is thrown
        super("The " + entity.getSimpleName().toLowerCase() + " with the id '" + id + "' does not exist in our records"); //passing an error message into the parent constructor allows us to access it later.
    }
}
