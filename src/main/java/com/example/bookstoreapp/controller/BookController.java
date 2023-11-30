package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.model.dto.StudentDto;
import com.example.bookstoreapp.model.dto.response.BookResponseDto;
import com.example.bookstoreapp.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookResponseDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{bookId}/readers")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<StudentDto>> getAllReadersForBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.getAllReadersForBook(bookId));
    }
}
