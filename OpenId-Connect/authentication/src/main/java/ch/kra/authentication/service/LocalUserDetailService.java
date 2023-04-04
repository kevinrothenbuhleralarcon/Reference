package ch.kra.authentication.service;

import ch.kra.authentication.dto.LocalUser;
import ch.kra.authentication.exception.ResourceNotFoundException;
import ch.kra.authentication.model.User;
import ch.kra.authentication.util.GeneralUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("localUserDetailService")
@AllArgsConstructor
public class LocalUserDetailService implements UserDetailsService {

    private UserService userService;

    @Override
    @Transactional
    public LocalUser loadUserByUsername(final String email) throws UsernameNotFoundException {
        Optional<User> oUser = userService.findUserByEmail(email);
        if (oUser.isEmpty()) {
            throw new UsernameNotFoundException("User " + email + " was not found in the database");
        }
        return createLocalUser(oUser.get());
    }

    @Transactional
    public LocalUser loadUserById(Long id) {
        User user = userService.findUserById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return createLocalUser(user);
    }

    /**
     * @param user
     * @return
     */
    private LocalUser createLocalUser(User user) {
        return new LocalUser(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, GeneralUtils.buildSimpleGrantedAuthorities(user.getRoles()), user);
    }
}
