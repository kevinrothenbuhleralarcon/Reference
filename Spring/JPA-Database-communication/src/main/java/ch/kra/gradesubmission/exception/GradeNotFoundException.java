package ch.kra.gradesubmission.exception;

public class GradeNotFoundException extends RuntimeException { 

    public GradeNotFoundException(final Long studentId, final Long courseId) {
        super("The grade with student id: '" + studentId + "' and course id: '" + courseId + "' does not exist in our records");
    }
    
}