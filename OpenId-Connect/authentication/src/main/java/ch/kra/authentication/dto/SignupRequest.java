package ch.kra.authentication.dto;

import ch.kra.authentication.validation.password.PasswordMatches;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SignupRequest {

    private Long userID;

    private String providerUserId;

    @NotEmpty
    private String displayName;

    @NotEmpty
    private String email;

    private AuthenticationProvider authenticationProvider;

    @Size(min = 6, message = "{Size.userDto.password}")
    private String password;

    @NotEmpty
    @PasswordMatches(baseField = "password", matchField = "matchingPassword", message = "Passwords don't match")
    private String matchingPassword;

//    public SignupRequest(String providerUserId, String displayName, String email, String password, AuthenticationProvider authenticationProvider) {
//        this.providerUserId = providerUserId;
//        this.displayName = displayName;
//        this.email = email;
//        this.password = password;
//        this.authenticationProvider = authenticationProvider;
//    }
}
