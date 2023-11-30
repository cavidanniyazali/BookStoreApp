package com.example.bookstoreapp.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentAuthenticationResponse extends AuthenticationResponse {
    private String token;
    private StudentResponseDto studentResponseDto;
}