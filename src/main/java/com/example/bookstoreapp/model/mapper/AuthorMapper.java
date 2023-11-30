package com.example.bookstoreapp.model.mapper;

import com.example.bookstoreapp.model.dto.response.AuthorResponseDto;
import com.example.bookstoreapp.model.entity.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface AuthorMapper {
    AuthorResponseDto convertToAuthorResponseDto(Author author);
}