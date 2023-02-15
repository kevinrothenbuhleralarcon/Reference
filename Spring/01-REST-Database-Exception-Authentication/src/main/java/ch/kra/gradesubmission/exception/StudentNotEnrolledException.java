package ch.kra.gradesubmission.exception;

public class StudentNotEnrolledException extends RuntimeException {
    // Throw it and use ControllerAdvice and ExceptionHandler to act accordingly, can create an errorResponse class to add a content to the responseEntity

    public StudentNotEnrolledException(final Long studentId, final Long courseId) {
        super("The student id '" + studentId + "' is not enrolled to course id '" + courseId + "'"); //passing an error message into the parent constructor allows us to access it later.
    }

}