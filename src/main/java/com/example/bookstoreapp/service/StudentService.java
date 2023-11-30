package com.example.bookstoreapp.service;

import com.example.bookstoreapp.config.security.service.CustomUserDetails;
import com.example.bookstoreapp.model.dto.request.StudentRequestDto;
import com.example.bookstoreapp.model.dto.response.BookResponseDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService {
    void createStudent(StudentRequestDto studentRequestDto);

    List<BookResponseDto> getAllReadingBooksByStudentId(Long studentId);

    BookResponseDto getBookByStudent(CustomUserDetails studentDetails, Long bookId);

    String subscribeAuthor(Long studentId, Long authorId);

}
