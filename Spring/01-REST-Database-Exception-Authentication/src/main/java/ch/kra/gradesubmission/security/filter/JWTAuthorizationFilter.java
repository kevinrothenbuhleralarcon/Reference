package ch.kra.gradesubmission.security.filter;

import ch.kra.gradesubmission.security.SecurityConfig;
import ch.kra.gradesubmission.security.jwt.JWTConfiguration;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@AllArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final JWTConfiguration jwtConfiguration;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader(SecurityConfig.AUTHORIZATION);

        if (header == null || !header.startsWith(SecurityConfig.BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.replace(SecurityConfig.BEARER, ""); // We retrieve only the token

        // Check if the signature of the token is the same as the one we generate otherwise throw an error
        String user = JWT.require(Algorithm.HMAC512(jwtConfiguration.getSecret()))
                .build()
                .verify(token)
                .getSubject();
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, List.of()); // !!! We need to use this constructor otherwise authentication is set to false !!!
        SecurityContextHolder.getContext().setAuthentication(authentication); // If we're here that means the token is valid and so we validate the authentication
        filterChain.doFilter(request, response);
    }
}
