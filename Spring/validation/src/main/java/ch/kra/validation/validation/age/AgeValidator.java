package ch.kra.validation.validation.age;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class AgeValidator implements ConstraintValidator<Age, LocalDate> {
    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(value)) {
            return false;
        }
        long diff = Duration.between(value, LocalDate.now()).toMillis();
        int age = (int) TimeUnit.MILLISECONDS.toDays(diff) / 365;
        return age >= 18;
    }
}
