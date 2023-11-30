//package com.example.bookstoreapp.controller;
//
//import com.example.bookstoreapp.config.security.service.CustomUserDetails;
//import com.example.bookstoreapp.model.dto.request.AuthenticationRequest;
//import com.example.bookstoreapp.model.dto.request.AuthorRequestDto;
//import com.example.bookstoreapp.model.dto.request.BookRequestDto;
//import com.example.bookstoreapp.model.dto.response.AuthorAuthenticationResponse;
//import com.example.bookstoreapp.model.dto.response.AuthorResponseDto;
//import com.example.bookstoreapp.model.entity.UserAuthentication;
//import com.example.bookstoreapp.model.mapper.AuthorMapperImpl;
//import com.example.bookstoreapp.service.AuthorService;
//import com.example.bookstoreapp.service.BookService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import org.junit.jupiter.api.*;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.RequestBuilder;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//
//@WebMvcTest(controllers = AuthorController.class)
//@Import(AuthorMapperImpl.class)
//@WithMockUser
//@RequiredArgsConstructor
//@ContextConfiguration(classes = {AuthorController.class})
//public class AuthorControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    @Mock
//    private AuthorService authorService;
//    @Mock
//    private UserAuthentication userAuthentication;
//    @Mock
//    private BookService bookService;
//
//    @InjectMocks
//    private AuthorController authorController;
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//    ObjectMapper mapper = new ObjectMapper();
//
////    @Test
////    @WithMockUser(username = "testuser", password = "testPassword", roles = {"STUDENT", "AUTHOR"})
////    void testAuth() throws Exception {
////        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
////        authenticationRequest.setEmail("testuser");
////        authenticationRequest.setPassword("testpassword");
////
////        AuthorAuthenticationResponse authenticationResponse = new AuthorAuthenticationResponse();
////        authenticationResponse.setToken("testtoken");
////        authenticationResponse.setAuthorResponseDto(new AuthorResponseDto());
////
////        when(userAuthenticationService.login(any(AuthenticationRequest.class)))
////                .thenReturn(authenticationResponse);
////
////        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
////                .post("/api/auth/login")
////                .with(csrf())
////                .contentType(MediaType.APPLICATION_JSON)
////                .content(objectMapper.writeValueAsString(authenticationRequest));
////
////        MvcResult mvcResult = mockMvc.perform(requestBuilder)
////                .andExpect(MockMvcResultMatchers.status().isOk())
////                .andReturn();
////
////        String jsonResponse = mvcResult.getResponse().getContentAsString();
////        AuthorAuthenticationResponse response = objectMapper.readValue(jsonResponse, AuthorAuthenticationResponse.class);
////        Assertions.assertNotNull(response);
////        Assertions.assertEquals("testtoken", response.getToken());
////    }
//    @Test
//    public void testCreateAuthor_whenValidAuthorRequestDto() throws Exception {
//        AuthorRequestDto authorRequestDto = new AuthorRequestDto();
//        authorRequestDto.setAge(1);
//        authorRequestDto.setName("test");
//        authorRequestDto.setEmail("test@gmail.com");
//        authorRequestDto.setPassword("test");
//        RequestBuilder requestBuilderPost = MockMvcRequestBuilders
//                .multipart(HttpMethod.POST, "/api/v1/authors")
//                .file(new MockMultipartFile("userDto", "", "application/json", mapper.writeValueAsString(authorRequestDto).getBytes()))
//                .with(csrf())
//                .accept(MediaType.APPLICATION_JSON_VALUE);
//
//
//        MvcResult mvcResultPost = mockMvc.perform(requestBuilderPost).andReturn();
//        assertEquals(HttpStatus.CREATED.value(), mvcResultPost.getResponse().getStatus());
//    }
//
////    @Test
////    public void testCreateBookByAuthor() throws Exception {
//////        CustomUserDetails userDetails = new CustomUserDetails(userAuthentication);
//////        BookRequestDto bookRequestDto = new BookRequestDto();
//////        bookRequestDto.setName("test");
//////        Mockito.when(authorService.createBookByAuthor(userDetails, bookRequestDto)).thenReturn("Successfully test");
//////
//////        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
//////                .post("/api/v1/authors/book")
//////                .with(csrf())
//////                .contentType(MediaType.APPLICATION_JSON)
//////                .content(mapper.writeValueAsString(bookRequestDto));
//////
//////        MvcResult mvcResult = mockMvc.perform(requestBuilder)
//////                .andExpect(MockMvcResultMatchers.status().isOk())
//////                .andReturn();
//////
//////        String jsonResponse = mvcResult.getResponse().getContentAsString();
//////        AuthorAuthenticationResponse response = mapper.readValue(jsonResponse, AuthorAuthenticationResponse.class);
//////        Assertions.assertNotNull(response);
//////        Assertions.assertEquals("Successfully test", response.getToken());
//////
////////        authorController.createBookByAuthor(userDetails, bookRequestDto);
//////////        assertEquals("Successfully test", result);
////////        Mockito.verify(authorService).createBookByAuthor(userDetails, bookRequestDto);
////        CustomUserDetails userDetails = new CustomUserDetails(userAuthentication); // Kullanıcı detayları oluştur
////        BookRequestDto bookRequestDto = new BookRequestDto(); // Kitap isteği oluştur
////
//////        AuthorService authorServiceMock = mock(AuthorService.class); // AuthorService'nin bir mock örneği oluştur
////        when(authorService.createBookByAuthor(any(), any())).thenReturn("Kitap başarıyla oluşturuldu"); // Metodun dönüş değerini ayarla
////
////        BookController bookController = new BookController(); // Controller'ın bir örneği oluştur
////        bookController.setAuthorService(authorServiceMock); // Mock AuthorService'i controller'a enjekte et
////
////        // Metodu çağır ve sonucu al
////        ResponseEntity<String> responseEntity = bookController.createBookByAuthor(userDetails, bookRequestDto);
////
////        // Sonucu kontrol et
////        assertEquals("Kitap başarıyla oluşturuldu", responseEntity.getBody());
////        assertEquals(200, responseEntity.getStatusCodeValue());
////    }
//
//    @Test
//    @WithMockUser(authorities = "AUTHOR")
//    public void testCreateBookByAuthor() throws Exception {
//        CustomUserDetails userDetails = new CustomUserDetails(userAuthentication);
//        BookRequestDto bookRequestDto = new BookRequestDto();
//        bookRequestDto.setName("Test");
//
//        when(authorService.createBookByAuthor(any(), any())).thenReturn("Successfully test");
//
//        BookController bookController = new BookController(bookService);
//
//        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
//
//        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
//                .post("/api/v1/authors/book")
//                .with(csrf())
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapper.writeValueAsString(bookRequestDto));
//
//        MvcResult mvcResult = mockMvc.perform(requestBuilder)
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//
//        String jsonResponse = mvcResult.getResponse().getContentAsString();
//        assertEquals("Successfully test", jsonResponse);
//    }
//
//    @Test
//    public void testDeleteBookByAuthor() {
//        CustomUserDetails userDetails = new CustomUserDetails(Mockito.any());
//        Long bookId = 1L;
//        Mockito.doNothing().when(authorService).deleteBookByAuthor(userDetails, bookId);
//        authorController.deleteBookByAuthor(userDetails, bookId);
//        Mockito.verify(authorService).deleteBookByAuthor(userDetails, bookId);
//    }
//}
//
