package com.example.SpringSecurityJWT.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/crackit/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final authService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest)
    {
        AuthenticationResponse authResponse =  authService.register(registerRequest);

        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticaionRequest request)
    {
       return ResponseEntity.ok(authService.authenticate(request));


    }

}
