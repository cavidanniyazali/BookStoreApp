package com.example.bookstoreapp.service.impl;

import com.example.bookstoreapp.exception.NotFoundException;
import com.example.bookstoreapp.model.dto.StudentDto;
import com.example.bookstoreapp.model.dto.request.BookRequestDto;
import com.example.bookstoreapp.model.dto.response.BookResponseDto;
import com.example.bookstoreapp.model.dto.response.StudentResponseDto;
import com.example.bookstoreapp.model.entity.Book;
import com.example.bookstoreapp.model.entity.Student;
import com.example.bookstoreapp.model.mapper.BookMapper;
import com.example.bookstoreapp.model.mapper.StudentMapper;
import com.example.bookstoreapp.repository.BookRepository;
import com.example.bookstoreapp.repository.StudentRepository;
import com.example.bookstoreapp.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final StudentMapper studentMapper;

    @Override
    public List<BookResponseDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        log.info("Fetching all books");
        return books.stream()
                .map(bookMapper::convertToBookResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentDto> getAllReadersForBook(Long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            return studentMapper.toStudentResponseDtos(optionalBook.get().getReaders());
        }
        throw new NotFoundException("Book not found");
    }
}
