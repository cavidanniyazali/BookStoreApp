package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.config.security.service.CustomUserDetails;
import com.example.bookstoreapp.model.dto.request.StudentRequestDto;
import com.example.bookstoreapp.model.dto.response.BookResponseDto;
import com.example.bookstoreapp.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createStudent(@Valid @RequestBody StudentRequestDto studentRequestDto) {
        studentService.createStudent(studentRequestDto);
    }

    @GetMapping("/{id}/books")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<List<BookResponseDto>> getAllReadingBooksByStudentId(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getAllReadingBooksByStudentId(id));
    }

    @GetMapping("/book/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<BookResponseDto> getBookByStudent(@AuthenticationPrincipal CustomUserDetails studentDetails, @PathVariable Long bookId) {
        return ResponseEntity.ok(studentService.getBookByStudent(studentDetails, bookId));
    }

    @PostMapping("/{studentId}/subscribe/author/{authorId}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<String> subscribeAuthor(@PathVariable Long studentId, @PathVariable Long authorId) {
        return ResponseEntity.ok(studentService.subscribeAuthor(studentId, authorId));
    }
}
