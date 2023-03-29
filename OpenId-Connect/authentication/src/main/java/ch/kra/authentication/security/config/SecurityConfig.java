package ch.kra.authentication.security.config;

import ch.kra.authentication.security.filter.JWTAuthorizationFilter;
import ch.kra.authentication.security.oauth2.CustomOidcUserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private final CustomOidcUserService customOidcUserService;

    @Bean
    @Order(1)
    public SecurityFilterChain authFilterChain(HttpSecurity http) throws Exception {
        /*
         * h2
         */
//        http
//                .headers().frameOptions().disable().and()
//                .authorizeHttpRequests()
//                .requestMatchers(new AntPathRequestMatcher("/h2/**")).permitAll();

        /*
         * endpoint
         */
        http
                .securityMatcher("/auth/**", "/oauth2/**")
                .cors().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/oauth2/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .oidcUserService(customOidcUserService); // To manage what we receive from OIDC Provider (ex Google https://en.wikipedia.org/wiki/List_of_OAuth_providers)
//                        .userService(); // To manage what we receive from OAuth2 Provider that does not support OpenID (ex LinkedIn)
        return http.build();
    }

    @Bean
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {

        http
                .securityMatcher("/api/**")
                .cors().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .csrf().disable()
                .authorizeHttpRequests()
                .anyRequest().authenticated();

        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
