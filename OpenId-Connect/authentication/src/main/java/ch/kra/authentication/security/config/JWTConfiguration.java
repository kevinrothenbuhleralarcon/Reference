package ch.kra.authentication.security.config;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JWTConfiguration {

    @NotNull
    private Integer validityDuration;

    @NotNull
    private String secret;
}
