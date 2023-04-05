package ch.kra.authentication.dto;

import ch.kra.authentication.validation.password.PasswordMatches;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/*
 * DTO for a regular login
 */

@RequiredArgsConstructor
@Getter
@Setter
@PasswordMatches(baseField = "password", matchField = "matchingPassword", message = "Passwords don't match")
public class SignupRequest {

    private Long userID;

    @NonNull
    private String providerUserId;

    @NonNull
    @NotEmpty
    private String displayName;

    @NonNull
    @NotEmpty
    private String email;

    @NonNull
    private AuthenticationProvider provider;

    @NonNull
    @Size(min = 6, message = "{Size.userDto.password}")
    private String password;

    @NotEmpty
    private String matchingPassword;
}
