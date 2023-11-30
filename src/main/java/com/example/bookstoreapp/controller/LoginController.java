package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.model.dto.request.AuthenticationRequest;
import com.example.bookstoreapp.model.dto.response.AuthenticationResponse;
import com.example.bookstoreapp.service.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
public class LoginController {
    private final UserAuthenticationService userAuthenticationService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthenticationResponse> auth(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        return ResponseEntity.ok(userAuthenticationService.login(authenticationRequest));
    }
}
