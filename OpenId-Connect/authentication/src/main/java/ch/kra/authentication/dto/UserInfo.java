package ch.kra.authentication.dto;

import lombok.Value;

import java.util.List;

@Value
public class UserInfo {
    private String id, displayName, email;
    private List<String> roles;
}
