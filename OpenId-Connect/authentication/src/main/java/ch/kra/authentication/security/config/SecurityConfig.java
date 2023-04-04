package ch.kra.authentication.security.config;

import ch.kra.authentication.security.filter.JWTAuthorizationFilter;
import ch.kra.authentication.security.jwt.TokenProviderService;
import ch.kra.authentication.security.oauth2.CookieAuthorizationRequestRepository;
import ch.kra.authentication.security.oauth2.CustomOidcUserService;
import ch.kra.authentication.security.oauth2.OAuth2AuthenticationSuccessHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.core.http.converter.OAuth2AccessTokenResponseHttpMessageConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    private final CustomOidcUserService customOidcUserService;
    private final JWTAuthorizationFilter jwtAuthorizationFilter;
    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;

    @Bean
//    @Order(1) // not used bo kept for example in case we use the other bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*
         * h2
         */
//        http
//                .headers().frameOptions().disable().and()
//                .authorizeHttpRequests()
//                .requestMatchers(new AntPathRequestMatcher("/h2/**")).permitAll();

        http
                .cors().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/auth/**", "/oauth2/**").permitAll()
                .anyRequest().authenticated()
                .and()

                /*
                 * oAuth2
                 */
                .oauth2Login()
                    /*
                     * By default, Spring oauth2 uses Session to store the authorizazion request.
                     * As this service is stateless, we need to save it manually.
                     */
                    .authorizationEndpoint()
//                        .authorizationRequestRepository(cookieAuthorizationRequestRepository)
                        .and()
                    .userInfoEndpoint()
                        .oidcUserService(customOidcUserService) // To manage what we receive from OIDC Provider (ex Google https://en.wikipedia.org/wiki/List_of_OAuth_providers)
//                        .userService(); // To manage what we receive from OAuth2 Provider that does not support OpenID (ex LinkedIn)
//                    .tokenEndpoint()
//                            .accessTokenResponseClient() // In case we need to do something special with the token (in case of linkedin login for example, we need to retrieve the token type).
                        .and()
                    .successHandler(oAuth2AuthenticationSuccessHandler);
        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    /*
     * Will not be used but kept for example
     */
//    @Bean
//    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
//
//        http
//                .securityMatcher("/api/**")
//                .cors().and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .csrf().disable()
//                .authorizeHttpRequests()
//                .anyRequest().authenticated();
//
//
//        return http.build();
//    }
}
