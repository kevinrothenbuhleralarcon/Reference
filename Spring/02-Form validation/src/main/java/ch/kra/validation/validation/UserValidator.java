package ch.kra.validation.validation;

import ch.kra.validation.model.Address;
import ch.kra.validation.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import java.util.Objects;


@Service
@AllArgsConstructor
public class UserValidator {
    private Validator validator;

    public void validate(final User user, final BindingResult result) {
        if (user.getShouldValidateAddress()) {
            BindException error = new BindException(user.getAddress(), "");
            validator.validate(user.getAddress(), error);
            error.getFieldErrors().forEach(fieldError -> result.rejectValue("address." + fieldError.getField(), Objects.requireNonNull(fieldError.getCode()), Objects.requireNonNull(fieldError.getDefaultMessage())));
        }
    }
}
