package ch.kra.authentication.security.oauth2;

import ch.kra.authentication.dto.AuthenticationProvider;
import ch.kra.authentication.exception.OAuth2AuthenticationProcessingException;

import java.util.Map;

/*
 * This factory is used to return the correct user info class depending on the registrationID
 */
public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {

        if (registrationId.equalsIgnoreCase(AuthenticationProvider.GOOGLE.getProviderType())) {
            return new GoogleOAuth2UserInfo(attributes);
        } else {
            throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
        }
    }
}
