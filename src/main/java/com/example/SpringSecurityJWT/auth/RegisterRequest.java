package com.example.SpringSecurityJWT.auth;

import com.example.SpringSecurityJWT.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String FirstName;

    private String LastName;

    private String email;

    private String password;

    private Role role;



}
