package ch.kra.authentication.service;

import ch.kra.authentication.dto.AuthenticationProvider;
import ch.kra.authentication.dto.LocalUser;
import ch.kra.authentication.dto.SignupRequest;
import ch.kra.authentication.exception.OAuth2AuthenticationProcessingException;
import ch.kra.authentication.exception.UserAlreadyExistException;
import ch.kra.authentication.model.Role;
import ch.kra.authentication.model.User;
import ch.kra.authentication.repository.RoleRepository;
import ch.kra.authentication.repository.UserRepository;
import ch.kra.authentication.security.oauth2.OAuth2UserInfo;
import ch.kra.authentication.security.oauth2.OAuth2UserInfoFactory;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

import static ch.kra.authentication.util.GeneralUtils.getAuthenticationProvider;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public User registerNewUser(final SignupRequest signupRequest) throws UserAlreadyExistException {
        if (signupRequest.getUserID() != null && userRepository.existsById(signupRequest.getUserID())) {
            throw new UserAlreadyExistException("User with User id " + signupRequest.getUserID() + " already exist");
        } else if (userRepository.existsByEmail(signupRequest.getEmail())) {
            throw new UserAlreadyExistException("User with email id " + signupRequest.getEmail() + " already exist");
        }
        User user = buildUser(signupRequest);
        Date now = Calendar.getInstance().getTime();
        user.setCreatedDate(now);
        user.setModifiedDate(now);
        user = userRepository.save(user);
        return user;
    }

    @Override
    public Optional<User> findUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional // Needed here otherwise the lazy loading of the roles is not done dirung the findUserByEmail and so we cannot access the roles of the user later
    public LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, attributes);
        if (!StringUtils.hasLength(oAuth2UserInfo.getName())) {
            throw new OAuth2AuthenticationProcessingException("Name not found from OAuth2 provider");
        } else if (!StringUtils.hasLength(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }
        SignupRequest userDetails = buildSignupRequest(registrationId, oAuth2UserInfo);
        Optional<User> oUser = findUserByEmail(oAuth2UserInfo.getEmail());
        User user;
        if (oUser.isPresent()) {
            user = oUser.get();
            if (!user.getProvider().equals(registrationId) && !user.getProvider().equals(AuthenticationProvider.LOCAL.getProviderType())) {
                throw new OAuth2AuthenticationProcessingException(
                        "Looks like you're signed up with " + user.getProvider() + " account. Please use your " + user.getProvider() + " account to login.");
            }
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(userDetails);
        }

        return LocalUser.create(user, attributes, idToken, userInfo);
    }

    private User updateExistingUser(User existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setDisplayName(oAuth2UserInfo.getName());
        return userRepository.save(existingUser);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    private User buildUser(final SignupRequest signupRequest) {
        User user = new User();
        user.setDisplayName(signupRequest.getDisplayName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        final HashSet<Role> roles = new HashSet<Role>();
        roles.add(roleRepository.findByName(Role.ROLE_USER));
        user.setRoles(roles);
        user.setProvider(signupRequest.getProvider().getProviderType());
        user.setEnabled(true);
        user.setProviderUserId(signupRequest.getProviderUserId());
        return user;
    }

    private SignupRequest buildSignupRequest(String registrationId, OAuth2UserInfo oAuth2UserInfo) {
        return new SignupRequest(
                oAuth2UserInfo.getId(),
                oAuth2UserInfo.getName(),
                oAuth2UserInfo.getEmail(),
                getAuthenticationProvider(registrationId),
                "changeit"
        );
    }
}
