package com.example.bookstoreapp.service;

import com.example.bookstoreapp.model.dto.StudentDto;
import com.example.bookstoreapp.model.dto.response.BookResponseDto;
import com.example.bookstoreapp.model.dto.response.StudentResponseDto;
import com.example.bookstoreapp.model.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<BookResponseDto> getAllBooks();
    List<StudentDto> getAllReadersForBook(Long bookId);
}
