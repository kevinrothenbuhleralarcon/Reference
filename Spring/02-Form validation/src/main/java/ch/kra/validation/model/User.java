package ch.kra.validation.model;


import ch.kra.validation.validation.age.Age;
import ch.kra.validation.validation.not_equal_fields.NotEqualFields;
import ch.kra.validation.validation.username.Username;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@NotEqualFields(baseField = "firstName", matchField = "lastName", message = "Last name cannot be the same as first name")
public class User {
    private static final int MIN_LENGTH = 2;
    private static final int MIN_LENGTH_USERNAME = 7;

    @NotBlank(message = "First name cannot be blank")
    @Size(min = MIN_LENGTH, message = "First name should be at least "+ MIN_LENGTH +" character long")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Size(min = MIN_LENGTH, message = "Last name should be at least "+ MIN_LENGTH +" character long")
    private String lastName;

    @NotBlank(message = "Username cannot be blank")
    @Size(min = MIN_LENGTH_USERNAME, message = "Username should be at least "+ MIN_LENGTH_USERNAME +" character long")
    @Username(message = "Cannot contain special characters or uppercase characters ")
    private String userName;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email is not in the correct format")
    private String email;

    private Boolean shouldValidateAddress = false;

    private Address address = new Address();

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "Date of birth must be in the past")
    @Age(message = "Must be at least 18")
    private LocalDate dateOfBirth;
}
