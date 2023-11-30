package com.example.bookstoreapp.model.mapper;

import com.example.bookstoreapp.model.dto.request.BookRequestDto;
import com.example.bookstoreapp.model.dto.response.BookResponseDto;
import com.example.bookstoreapp.model.entity.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookResponseDto convertToBookResponseDto(Book book);
    Book convertToBook(BookRequestDto bookRequestDtos);
}
