package com.example.bookstoreapp.config.security.service;


import com.example.bookstoreapp.model.entity.UserAuthentication;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    private final UserAuthentication userAuthentication;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (userAuthentication.getAuthor() == null) {
            return List.of(new SimpleGrantedAuthority(userAuthentication.getStudent().getRole().getName()));
        }
        return List.of(new SimpleGrantedAuthority(userAuthentication.getAuthor().getRole().getName()));
    }

    @Override
    public String getPassword() {
        if (userAuthentication.getAuthor() == null) {
            return userAuthentication.getStudent().getUserAuthentication().getPassword();
        }
        return userAuthentication.getAuthor().getUserAuthentication().getPassword();
    }

    @Override
    public String getUsername() {
        if (userAuthentication.getAuthor() == null) {
            return userAuthentication.getStudent().getUserAuthentication().getEmail();
        }
        return userAuthentication.getAuthor().getUserAuthentication().getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
