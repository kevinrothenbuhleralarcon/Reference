package ch.kra.gradesubmission.exception;

public class StudentNotFoundException extends RuntimeException { 

    public StudentNotFoundException(final Long id) {
        super("The student id '" + id + "' does not exist in our records");
    }
    
}