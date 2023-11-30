//package com.example.bookstoreapp.controller;
//
//import com.example.bookstoreapp.config.security.service.CustomUserDetails;
//import com.example.bookstoreapp.model.dto.AuthorDto;
//import com.example.bookstoreapp.model.dto.RoleDto;
//import com.example.bookstoreapp.model.dto.request.StudentRequestDto;
//import com.example.bookstoreapp.model.dto.response.BookResponseDto;
//import com.example.bookstoreapp.service.StudentService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class StudentControllerTest {
//
//    @Mock
//    private StudentService studentService;
//
//    @InjectMocks
//    private StudentController studentController;
//
//    @Test
//    public void testCreateStudent() {
//        StudentRequestDto studentRequestDto = new StudentRequestDto();
//
//        Mockito.doNothing().when(studentService).createStudent(studentRequestDto);
//
//        studentController.createStudent(studentRequestDto);
//
//        Mockito.verify(studentService).createStudent(studentRequestDto);
//    }
//
//    @Test
//    public void testGetAllReadingBooksByStudentId() {
//        Long studentId = 1L;
//
//        List<BookResponseDto> mockResponse = Arrays.asList(new BookResponseDto("",new AuthorDto("",1,new RoleDto())));
//        Mockito.when(studentService.getAllReadingBooksByStudentId(studentId)).thenReturn(mockResponse);
//
////        List<BookResponseDto> result = studentController.getAllReadingBooksByStudentId(studentId);
//
////        Assertions.assertEquals(mockResponse, result);
//
//        Mockito.verify(studentService).getAllReadingBooksByStudentId(studentId);
//    }
//
//    @Test
//    public void testGetBookByStudent() {
//        CustomUserDetails userDetails = new CustomUserDetails(Mockito.any());
//        Long bookId = 1L;
//
//        BookResponseDto mockResponse = new BookResponseDto();
//        Mockito.when(studentService.getBookByStudent(userDetails, bookId)).thenReturn(mockResponse);
//
////        BookResponseDto result = studentController.getBookByStudent(userDetails, bookId);
//
////        Assertions.assertEquals(mockResponse, result);
//
//        Mockito.verify(studentService).getBookByStudent(userDetails, bookId);
//    }
//
//    @Test
//    public void testSubscribeAuthor() {
//        Long studentId = 1L;
//        Long authorId = 2L;
//
//        String mockResponse = "Successfully subscription";
//        Mockito.when(studentService.subscribeAuthor(studentId, authorId)).thenReturn(mockResponse);
//
////        String result = studentController.subscribeAuthor(studentId, authorId);
//
////        Assertions.assertEquals(mockResponse, result);
//
//        Mockito.verify(studentService).subscribeAuthor(studentId, authorId);
//    }
//}
//
