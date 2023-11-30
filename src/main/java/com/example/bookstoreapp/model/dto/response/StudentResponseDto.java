package com.example.bookstoreapp.model.dto.response;

import com.example.bookstoreapp.model.dto.RoleDto;
import com.example.bookstoreapp.model.entity.Author;
import com.example.bookstoreapp.model.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponseDto {
    private String name;
    private int age;
    private List<BookResponseDto> readingBooks;
//    private List<AuthorResponseDto> subscriptions;
    private RoleDto role;
}
