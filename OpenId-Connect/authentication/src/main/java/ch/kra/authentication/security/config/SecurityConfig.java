package ch.kra.authentication.security.config;

import ch.kra.authentication.security.oauth2.CustomOidcUserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private final CustomOidcUserService customOidcUserService;

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http
                .cors()
                    .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/", "/error", "api/all", "api/auth", "/h2/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                    .userInfoEndpoint()
                        .oidcUserService(customOidcUserService); // To manage what we receive from OIDC Provider (ex Google https://en.wikipedia.org/wiki/List_of_OAuth_providers)
//                        .userService(); // To manage what we receive from OAuth2 Provider that does not support OpenID (ex LinkedIn)
        return http.build();
    }
}
