package com.example.bookstoreapp.model.dto.response;

import com.example.bookstoreapp.model.dto.RoleDto;
import com.example.bookstoreapp.model.entity.Book;
import com.example.bookstoreapp.model.entity.Role;
import com.example.bookstoreapp.model.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponseDto {
    private String name;
    private int age;
    private List<BookResponseDto> books;
//    private List<StudentResponseDto> followers;
    private RoleDto role;
}
