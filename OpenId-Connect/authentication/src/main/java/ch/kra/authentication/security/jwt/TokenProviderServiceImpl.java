package ch.kra.authentication.security.jwt;

import ch.kra.authentication.dto.LocalUser;
import ch.kra.authentication.security.config.JWTConfiguration;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
@Slf4j
public class TokenProviderServiceImpl implements TokenProviderService {

    private final JWTConfiguration jwtConfiguration;

    @Override
    public String createToken(Authentication authentication) {
        LocalUser user = (LocalUser) authentication.getPrincipal();
        return JWT.create()
                .withSubject(user.getUser().getId().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtConfiguration.getValidityDuration()))
                .sign(Algorithm.HMAC512(jwtConfiguration.getSecret()));
    }

    @Override
    public Long getUserId(String token) {
        String subject = JWT.require(Algorithm.HMAC512(jwtConfiguration.getSecret()))
                .build()
                .verify(token)
                .getSubject();
        try {
            return Long.parseLong(subject);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
