package com.example.SpringSecurityJWT.auth;

import com.example.SpringSecurityJWT.User;


import com.example.SpringSecurityJWT.config.JWTService;
import com.example.SpringSecurityJWT.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class authService {

    private final UserRepository userRepository;

    private final JWTService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest registerRequest)
    {
        var user =User.builder().
                FirsName(registerRequest.getFirstName())
                .LastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(registerRequest.getRole())
                .build();

       var saveduser = userRepository.save(user);
        String  JwtToken =   jwtService.generateToken(saveduser);
       return  AuthenticationResponse.builder().accestoken(JwtToken).build();
    }

    public  AuthenticationResponse authenticate(AuthenticaionRequest request)
    {
       authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(request.getEmail(),
         request.getPassword()));


       var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
      String JwtToken =  jwtService.generateToken(user);
      return AuthenticationResponse.builder().accestoken(JwtToken).build();
    }
}
