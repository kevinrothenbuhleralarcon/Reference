package ch.kra.authentication.validation.password;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
public @interface PasswordMatches {
    String message() default "FIELDS DON'T MATCH";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String baseField();
    String matchField();
}
