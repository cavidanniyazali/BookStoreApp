package com.example.bookstoreapp.service.impl;

import com.example.bookstoreapp.config.security.jwt.JwtTokenUtil;
import com.example.bookstoreapp.exception.NotFoundException;
import com.example.bookstoreapp.model.dto.request.AuthenticationRequest;
import com.example.bookstoreapp.model.dto.response.AuthenticationResponse;
import com.example.bookstoreapp.model.dto.response.AuthorAuthenticationResponse;
import com.example.bookstoreapp.model.dto.response.StudentAuthenticationResponse;
import com.example.bookstoreapp.model.entity.UserAuthentication;
import com.example.bookstoreapp.model.entity.VerificationToken;
import com.example.bookstoreapp.model.mapper.AuthorMapper;
import com.example.bookstoreapp.model.mapper.StudentMapper;
import com.example.bookstoreapp.repository.UserAuthenticationRepository;
import com.example.bookstoreapp.repository.VerificationTokenRepository;
import com.example.bookstoreapp.service.UserAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthenticationServiceImpl implements UserAuthenticationService {
    private final UserAuthenticationRepository userAuthRepository;
    private final AuthenticationManager authenticationManager;
    private final StudentMapper studentMapper;
    private final AuthorMapper authorMapper;
    private final JwtTokenUtil jwtTokenUtil;
    private final VerificationTokenRepository verificationTokenRepository;

    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) throws Exception {
        Authentication authentication = authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        UserAuthentication userAuthentication = userAuthRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow(() ->
                new NotFoundException(String.format("email with %s not found", authenticationRequest.getEmail())));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final String token = jwtTokenUtil.generateToken(userDetails);
        if (verificationTokenRepository.findByUserAuthentication(userAuthentication).isPresent()) {
            VerificationToken verificationToken = verificationTokenRepository.findByUserAuthentication(userAuthentication).get();
            verificationTokenRepository.delete(verificationToken);
        }
        VerificationToken verificationToken = new VerificationToken(userAuthentication);
        verificationTokenRepository.save(verificationToken);

        if (userAuthentication.getAuthor() != null) {
            return AuthorAuthenticationResponse.builder().token(token)
                    .authorResponseDto(authorMapper.convertToAuthorResponseDto(userAuthentication.getAuthor())).build();
        } else {
            return StudentAuthenticationResponse.builder().token(token)
                    .studentResponseDto(studentMapper.toStudentResponseDto(userAuthentication.getStudent())).build();
        }
    }

    private Authentication authenticate(String email, String password) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
