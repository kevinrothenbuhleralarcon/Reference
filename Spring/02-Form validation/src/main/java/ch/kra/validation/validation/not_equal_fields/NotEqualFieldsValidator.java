package ch.kra.validation.validation.not_equal_fields;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Objects;

public class NotEqualFieldsValidator implements ConstraintValidator<NotEqualFields, Object> {

    private String baseField;
    private String matchField;

    @Override
    public void initialize(NotEqualFields constraint) {
        baseField = constraint.baseField();
        matchField = constraint.matchField();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        try {
            Object baseFieldValue = getFieldValue(object, baseField);
            Object matchFieldValue = getFieldValue(object, matchField);
            if (Objects.isNull(baseFieldValue) && Objects.isNull(matchFieldValue)) {
                return true;
            }
            return !baseFieldValue.equals(matchFieldValue);
        } catch (Exception e) {
            return false;
        }
    }

    private Object getFieldValue(Object object, String fieldName) throws Exception {
        Class<?> clazz = object.getClass();
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }

}
