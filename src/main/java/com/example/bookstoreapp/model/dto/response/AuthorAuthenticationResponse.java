package com.example.bookstoreapp.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorAuthenticationResponse extends AuthenticationResponse {
    private String token;
    private AuthorResponseDto authorResponseDto;
}