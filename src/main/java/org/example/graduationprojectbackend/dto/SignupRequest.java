package org.example.graduationprojectbackend.dto;

import lombok.Data;
import java.util.Set;

@Data
public class SignupRequest {
    private String username;
    private String email;
    private String password;
    private String verificationCode;
    private Set<String> roles;
}
