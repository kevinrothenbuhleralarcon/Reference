package ch.kra.authentication.util;

import ch.kra.authentication.dto.AuthenticationProvider;
import ch.kra.authentication.dto.LocalUser;
import ch.kra.authentication.dto.UserInfo;
import ch.kra.authentication.model.Role;
import ch.kra.authentication.model.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GeneralUtils {

    public static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(final Set<Role> roles) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    public static AuthenticationProvider toSocialProvider(String providerId) {
        for (AuthenticationProvider authenticationProvider : AuthenticationProvider.values()) {
            if (authenticationProvider.getProviderType().equals(providerId)) {
                return authenticationProvider;
            }
        }
        return AuthenticationProvider.LOCAL;
    }

    public static UserInfo buildUserInfo(LocalUser localUser) {
        List<String> roles = localUser.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
        User user = localUser.getUser();
        return new UserInfo(user.getId().toString(), user.getDisplayName(), user.getEmail(), roles);
    }
}
