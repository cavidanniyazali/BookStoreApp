package com.example.bookstoreapp.service;

import com.example.bookstoreapp.model.dto.request.AuthenticationRequest;
import com.example.bookstoreapp.model.dto.response.AuthenticationResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserAuthenticationService {
    AuthenticationResponse login(AuthenticationRequest authenticationRequest) throws Exception;
}
