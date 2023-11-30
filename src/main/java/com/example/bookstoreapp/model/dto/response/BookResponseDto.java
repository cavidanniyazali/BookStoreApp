package com.example.bookstoreapp.model.dto.response;

import com.example.bookstoreapp.model.dto.AuthorDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookResponseDto {
    private String name;
    private AuthorDto author;
}
