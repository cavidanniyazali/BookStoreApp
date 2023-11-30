package com.example.bookstoreapp.config.security.service;

import com.example.bookstoreapp.model.entity.UserAuthentication;
import com.example.bookstoreapp.repository.UserAuthenticationRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserAuthenticationRepository userAuthRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<UserAuthentication> userAuthOptional = userAuthRepository.findByEmail(email);
        if (userAuthOptional.isPresent()) {
            UserAuthentication userAuth = userAuthOptional.get();
            if (userAuth.getStudent() != null) {
                return new CustomUserDetails(userAuth.getStudent().getUserAuthentication());
            } else if (userAuth.getAuthor() != null) {
                return new CustomUserDetails(userAuth.getAuthor().getUserAuthentication());
            }
        }
        throw new UsernameNotFoundException("Student or author not found");
    }
}