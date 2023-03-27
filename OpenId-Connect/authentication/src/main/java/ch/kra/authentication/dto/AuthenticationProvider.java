package ch.kra.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AuthenticationProvider {
    GOOGLE("google"),
    LOCAL("local");

    private final String providerType;
}
