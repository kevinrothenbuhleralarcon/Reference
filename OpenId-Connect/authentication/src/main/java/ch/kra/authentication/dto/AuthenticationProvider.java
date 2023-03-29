package ch.kra.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AuthenticationProvider {
    GOOGLE("google"),
    LOCAL("local");

    private final String providerType;
}
