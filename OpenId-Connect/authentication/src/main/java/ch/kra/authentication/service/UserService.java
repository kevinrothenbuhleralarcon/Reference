package ch.kra.authentication.service;

import ch.kra.authentication.dto.LocalUser;
import ch.kra.authentication.dto.SignupRequest;
import ch.kra.authentication.exception.UserAlreadyExistException;
import ch.kra.authentication.model.User;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.Map;
import java.util.Optional;

public interface UserService {

    public User registerNewUser(SignupRequest signUpRequest) throws UserAlreadyExistException;

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserById(Long id);

    OidcUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo);
}
