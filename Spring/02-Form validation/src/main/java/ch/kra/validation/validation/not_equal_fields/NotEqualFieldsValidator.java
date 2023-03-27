package ch.kra.validation.validation.not_equal_fields;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Objects;

public class NotEqualFieldsValidator implements ConstraintValidator<NotEqualFields, Object> {

    private String baseField;
    private String matchField;
    private String message;

    @Override
    public void initialize(NotEqualFields constraint) {
        baseField = constraint.baseField();
        matchField = constraint.matchField();
        message = constraint.message();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        boolean isvalid;
        try {
            Object baseFieldValue = getFieldValue(object, baseField);
            Object matchFieldValue = getFieldValue(object, matchField);
            if (Objects.isNull(baseFieldValue) && Objects.isNull(matchFieldValue)) {
                isvalid = true;
            }
            isvalid =  !baseFieldValue.equals(matchFieldValue);
        } catch (Exception e) {
            isvalid = false;
        }

        if (!isvalid) {
            constraintValidatorContext.disableDefaultConstraintViolation(); // so the error is not linked to the class

            // Assign the constraint violation on the matching field
            constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(matchField)
                    .addConstraintViolation();
        }

        return isvalid;
    }

    private Object getFieldValue(Object object, String fieldName) throws Exception {
        Class<?> clazz = object.getClass();
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }

}
