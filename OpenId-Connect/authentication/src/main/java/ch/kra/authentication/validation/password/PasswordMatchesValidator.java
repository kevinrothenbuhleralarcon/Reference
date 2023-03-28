package ch.kra.authentication.validation.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Field;
import java.util.Objects;


public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    private String baseField;
    private String matchField;
    private String message;

    @Override
    public void initialize(PasswordMatches constraint) {
        baseField = constraint.baseField();
        matchField = constraint.matchField();
        message = constraint.message();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid;
        try {
            Object baseFieldValue = getFieldValue(object, baseField);
            Object matchFieldValue = getFieldValue(object, matchField);
            if (Objects.isNull(baseFieldValue) && Objects.isNull(matchFieldValue)) {
                isValid = true;
            } else {
                isValid = baseFieldValue.equals(matchFieldValue);
            }
        } catch (Exception e) {
            isValid = false;
        }

        if (!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation(); // so the error is not linked to the class

            // Assign the constraint violation on the matching field
            constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(baseField)
                    .addConstraintViolation();
        }

        return isValid;
    }

    private Object getFieldValue(Object object, String fieldName) throws Exception {
        Class<?> clazz = object.getClass();
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }
}
