package ch.kra.authentication.security.jwt;

import org.springframework.security.core.Authentication;

public interface TokenProviderService {
    public String createToken(Authentication authentication);
    public Long getUserId(String token);
}
