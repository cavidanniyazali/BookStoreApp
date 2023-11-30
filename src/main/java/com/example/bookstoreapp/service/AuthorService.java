package com.example.bookstoreapp.service;

import com.example.bookstoreapp.config.security.service.CustomUserDetails;
import com.example.bookstoreapp.model.dto.request.AuthorRequestDto;
import com.example.bookstoreapp.model.dto.request.BookRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthorService {
    void createAuthor(AuthorRequestDto authorRequestDto);

    String createBookByAuthor(CustomUserDetails authorDetails, BookRequestDto bookRequestDtos);

    void deleteBookByAuthor(CustomUserDetails authorDetails, Long bookId);
}
