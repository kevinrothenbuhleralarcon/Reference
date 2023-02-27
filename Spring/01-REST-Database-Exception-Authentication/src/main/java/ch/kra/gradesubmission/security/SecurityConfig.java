package ch.kra.gradesubmission.security;


import ch.kra.gradesubmission.api.Routes;
import ch.kra.gradesubmission.security.filter.AuthenticationFilter;
import ch.kra.gradesubmission.security.filter.ExceptionHandlerFilter;
import ch.kra.gradesubmission.security.filter.JWTAuthorizationFilter;
import ch.kra.gradesubmission.security.jwt.JWTConfiguration;
import ch.kra.gradesubmission.security.manager.CustomAuthenticationManager;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@AllArgsConstructor
public class SecurityConfig {
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";

    private final AuthenticationManager authenticationManager;
    private final JWTConfiguration jwtConfiguration;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        final AuthenticationFilter authenticationFilter = new AuthenticationFilter((CustomAuthenticationManager) authenticationManager, jwtConfiguration);
        authenticationFilter.setFilterProcessesUrl("/authenticate");
        http
                .headers().frameOptions().disable() // New Line: the h2 console runs on a "frame". By default, Spring Security prevents rendering within an iframe. This line disables its prevention.
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/h2/**").permitAll() // New Line: allows us to access the h2 console without the need to authenticate. ' ** '  instead of ' * ' because multiple path levels will follow /h2.
                .antMatchers(HttpMethod.POST, Routes.REGISTER).permitAll()
                .anyRequest().authenticated()  // So that all routes needs a credential
                .and()
                .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class) // Set a filter before the authentication filter to handle the exception and return a 400 error
                .addFilter(authenticationFilter)
                .addFilterAfter(new JWTAuthorizationFilter(jwtConfiguration), AuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // So a session token is not created after a login and all requests need a credential
        return http.build();
    }
}
