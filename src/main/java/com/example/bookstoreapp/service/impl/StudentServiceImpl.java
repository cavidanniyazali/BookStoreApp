package com.example.bookstoreapp.service.impl;

import com.example.bookstoreapp.config.security.service.CustomUserDetails;
import com.example.bookstoreapp.exception.AlreadyExistsException;
import com.example.bookstoreapp.exception.NotFoundException;
import com.example.bookstoreapp.exception.PasswordException;
import com.example.bookstoreapp.model.dto.request.StudentRequestDto;
import com.example.bookstoreapp.model.dto.response.BookResponseDto;
import com.example.bookstoreapp.model.entity.Author;
import com.example.bookstoreapp.model.entity.Book;
import com.example.bookstoreapp.model.entity.Student;
import com.example.bookstoreapp.model.entity.UserAuthentication;
import com.example.bookstoreapp.model.mapper.BookMapper;
import com.example.bookstoreapp.repository.*;
import com.example.bookstoreapp.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService {
    private final AuthorRepository authorRepository;
    private final StudentRepository studentRepository;
    private final BookMapper bookMapper;
    private final UserAuthenticationRepository userAuthRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final BookRepository bookRepository;

    @Override
    public void createStudent(StudentRequestDto studentRequestDto) {
        if (userAuthRepository.findByEmail(studentRequestDto.getEmail()).isPresent()) {
            throw new AlreadyExistsException(String.format("email with %s already exists", studentRequestDto.getEmail()));
        }
        if (!studentRequestDto.getPassword().equals(studentRequestDto.getConfirmPassword())) {
            throw new PasswordException("Password is wrong");
        }
        Student student = getStudent(studentRequestDto);
        UserAuthentication userAuth = getUserAuthentication(studentRequestDto.getEmail(), studentRequestDto.getPassword(), student);
        student.setUserAuthentication(userAuth);
        userAuth.setStudent(student);
        student.setRole(roleRepository.findByName("STUDENT"));
        studentRepository.save(student);
        userAuthRepository.save(userAuth);
        log.info("Creating new student {}", student.getName());
    }

    @Override
    public List<BookResponseDto> getAllReadingBooksByStudentId(Long studentId) {
        Student student = studentRepository.getReferenceById(studentId);
        Set<Book> readingBooks = student.getReadingBooks();
        log.info("Fetching all reading books of the student");
        return readingBooks.stream()
                .map(bookMapper::convertToBookResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookResponseDto getBookByStudent(CustomUserDetails studentDetails, Long bookId) {
        Student student = studentDetails.getUserAuthentication().getStudent();
        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            Set<Book> bookSet = student.getReadingBooks();
            bookSet.add(book);
            student.setReadingBooks(bookSet);
            studentRepository.save(student);
            return bookMapper.convertToBookResponseDto(book);
        } else {
            throw new NotFoundException("Book not found");
        }
    }


    @Override
    public String subscribeAuthor(Long studentId, Long authorId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            Optional<Author> optionalAuthor = authorRepository.findById(authorId);
            if (optionalAuthor.isPresent()) {
                Author author = optionalAuthor.get();
                Student student = optionalStudent.get();
                List<Author> authors = student.getSubscriptions();
                if (!(authors.contains(author))) {
                    authors.add(author);
                    student.setSubscriptions(authors);
                    studentRepository.save(student);
                    return "Subscription successfully!";
                }
                return "The author is already subscribed to!";
            }
            throw new NotFoundException("Author not found");
        }
        throw new NotFoundException("Student not found");
    }

    private Student getStudent(StudentRequestDto studentRequestDto) {
        return Student.builder()
                .name(studentRequestDto.getName())
                .age(studentRequestDto.getAge())
                .build();
    }

    private UserAuthentication getUserAuthentication(String email, String password, Student student) {
        return new UserAuthentication(email, passwordEncoder.encode(password), student);
    }
}
