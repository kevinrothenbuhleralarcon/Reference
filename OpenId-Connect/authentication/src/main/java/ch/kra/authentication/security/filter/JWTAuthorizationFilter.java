package ch.kra.authentication.security.filter;

import ch.kra.authentication.security.config.SecurityConfig;
import ch.kra.authentication.security.jwt.TokenProviderService;
import ch.kra.authentication.service.LocalUserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@AllArgsConstructor
@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final LocalUserDetailService localUserDetailService;
    private final TokenProviderService tokenProviderService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getJwtFromRequest(request);

        if (Objects.isNull(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Throws a JwtValidationException if the token is not valid
        Long id = tokenProviderService.getUserId(token);

        if (Objects.isNull(id)) {
            filterChain.doFilter(request, response);
            return;
        }

        // Throws a ResourceNotFoundException if the user is not found
        UserDetails userDetails = localUserDetailService.loadUserById(id);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String header = request.getHeader(SecurityConfig.AUTHORIZATION);
        if (!StringUtils.hasLength(header) || !header.startsWith(SecurityConfig.BEARER)) {
            return null;
        }
        return header.replace(SecurityConfig.BEARER, "");
    }
}
