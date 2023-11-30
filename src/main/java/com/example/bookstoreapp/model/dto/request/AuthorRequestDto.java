package com.example.bookstoreapp.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequestDto {
    @NotBlank(message = "Name is mandatory")
    @Pattern(regexp = "^[A-Z][a-zA-Z]*$", message = "Numbers and the first letter starting with a lowercase letter not allowed")
    private String name;
    @NotNull
    @NotBlank(message = "Password is mandatory")
    private String password;
    @NotNull
    @NotBlank(message = "Confirm password is mandatory")
    private String confirmPassword;
    @NotNull
    @NotBlank(message = "Email is mandatory")
    @Email(regexp = "^(.+)@(.+)$", message = "Email is wrong")
    private String email;
    private int age;
}
