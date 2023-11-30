package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.config.security.service.CustomUserDetails;
import com.example.bookstoreapp.model.dto.request.AuthorRequestDto;
import com.example.bookstoreapp.model.dto.request.BookRequestDto;
import com.example.bookstoreapp.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createAuthor(@Valid @RequestBody AuthorRequestDto authorRequestDto) {
        authorService.createAuthor(authorRequestDto);
    }

    @PostMapping("/book")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('AUTHOR')")
    public ResponseEntity<String> createBookByAuthor(@AuthenticationPrincipal CustomUserDetails authorDetails, @RequestBody BookRequestDto bookRequestDtos) {
        return ResponseEntity.ok(authorService.createBookByAuthor(authorDetails, bookRequestDtos));
    }

    @DeleteMapping("/book/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('AUTHOR')")
    public void deleteBookByAuthor(@AuthenticationPrincipal CustomUserDetails authorDetails, @PathVariable Long bookId) {
        authorService.deleteBookByAuthor(authorDetails, bookId);
    }
}