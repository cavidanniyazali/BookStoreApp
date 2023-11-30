//package com.example.bookstoreapp.controller;
//
//import com.example.bookstoreapp.model.dto.AuthorDto;
//import com.example.bookstoreapp.model.dto.RoleDto;
//import com.example.bookstoreapp.model.dto.StudentDto;
//import com.example.bookstoreapp.model.dto.response.BookResponseDto;
//import com.example.bookstoreapp.model.mapper.AuthorMapperImpl;
//import com.example.bookstoreapp.service.BookService;
//import lombok.RequiredArgsConstructor;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.ContextConfiguration;
//
//import java.util.Arrays;
//import java.util.List;
//
//@WebMvcTest(controllers = BookController.class)
//@WithMockUser
//@RequiredArgsConstructor
//@ContextConfiguration(classes = {BookController.class})
//public class BookControllerTest {
//
//    @Mock
//    private BookService bookService;
//
//    @InjectMocks
//    private BookController bookController;
//
//    @Test
//    public void testGetAllBooks() {
//        List<BookResponseDto> mockResponse = Arrays.asList(new BookResponseDto("test",new AuthorDto("",1,new RoleDto())));
//        Mockito.when(bookService.getAllBooks()).thenReturn(mockResponse);
//
//        List<BookResponseDto> result = bookController.getAllBooks();
//
//        Assertions.assertEquals(mockResponse, result);
//
//        Mockito.verify(bookService).getAllBooks();
//    }
//
//    @Test
//    public void testGetAllReadersForBook() {
//        Long bookId = 1L;
//
//        List<StudentDto> mockResponse = Arrays.asList(new StudentDto());
//        Mockito.when(bookService.getAllReadersForBook(bookId)).thenReturn(mockResponse);
//
////        List<StudentDto> result = bookController.getAllReadersForBook(bookId);
//
////        Assertions.assertEquals(mockResponse, result);
//
//        Mockito.verify(bookService).getAllReadersForBook(bookId);
//    }
//}
//
