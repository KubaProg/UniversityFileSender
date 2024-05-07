package pl.polsl.universityfilesender.security.dto;

import lombok.Getter;

@Getter
public class AuthenticationRequest {
    private String username;
    private String password;
}
