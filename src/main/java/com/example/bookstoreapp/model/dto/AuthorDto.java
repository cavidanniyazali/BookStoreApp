package com.example.bookstoreapp.model.dto;

import com.example.bookstoreapp.model.dto.response.BookResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
    private String name;
    private int age;
    private RoleDto role;
}