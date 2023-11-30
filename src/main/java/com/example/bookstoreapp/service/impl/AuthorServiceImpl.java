package com.example.bookstoreapp.service.impl;

import com.example.bookstoreapp.config.security.service.CustomUserDetails;
import com.example.bookstoreapp.exception.AlreadyExistsException;
import com.example.bookstoreapp.exception.NotFoundException;
import com.example.bookstoreapp.exception.PasswordException;
import com.example.bookstoreapp.model.dto.request.AuthorRequestDto;
import com.example.bookstoreapp.model.dto.request.BookRequestDto;
import com.example.bookstoreapp.model.entity.Author;
import com.example.bookstoreapp.model.entity.Book;
import com.example.bookstoreapp.model.entity.Student;
import com.example.bookstoreapp.model.entity.UserAuthentication;
import com.example.bookstoreapp.model.mapper.BookMapper;
import com.example.bookstoreapp.repository.*;
import com.example.bookstoreapp.service.AuthorService;
import com.example.bookstoreapp.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorServiceImpl implements AuthorService {
    private final StudentRepository studentRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;
    private final UserAuthenticationRepository userAuthRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final BookRepository bookRepository;
    private final EmailService emailService;

    @Override
    public void createAuthor(AuthorRequestDto authorRequestDto) {
        if (userAuthRepository.findByEmail(authorRequestDto.getEmail()).isPresent()) {
            throw new AlreadyExistsException(String.format("email with %s already exists", authorRequestDto.getEmail()));
        }
        if (!authorRequestDto.getPassword().equals(authorRequestDto.getConfirmPassword())) {
            throw new PasswordException("Password is wrong");
        }
        Author author = getAuthor(authorRequestDto);
        UserAuthentication userAuth = getUserAuthentication(authorRequestDto.getEmail(), authorRequestDto.getPassword(), author);
        author.setUserAuthentication(userAuth);
        userAuth.setAuthor(author);
        author.setRole(roleRepository.findByName("AUTHOR"));
        authorRepository.save(author);
        userAuthRepository.save(userAuth);
        log.info("Creating new author {}", author.getName());
    }

    @Override
    public String createBookByAuthor(CustomUserDetails authorDetails, BookRequestDto bookRequestDtos) {
        Author author = authorDetails.getUserAuthentication().getAuthor();
        Book book = bookMapper.convertToBook(bookRequestDtos);
        book.setAuthor(author);
        bookRepository.save(book);
        return emailService.notifySubscribedStudentsWithEmail(author, book);
    }

    @Override
    public void deleteBookByAuthor(CustomUserDetails authorDetails, Long bookId) {
        Author author = authorDetails.getUserAuthentication().getAuthor();
        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if (optionalBook.isPresent()) {
            Book bookToDelete = optionalBook.get();
            List<Book> books = bookRepository.getBooksByAuthor(author);
            for (Student student : bookToDelete.getReaders()) {
                Set<Book> bookSet = student.getReadingBooks();
                if (bookSet.contains(bookToDelete)) {
                    bookSet.remove(bookToDelete);
                    student.setReadingBooks(bookSet);
                    studentRepository.save(student);
                }
            }
            for (Book book : books) {
                if (book.equals(bookToDelete)) {
                    bookRepository.delete(book);
                }
            }
        } else {
            throw new NotFoundException("Book not found");
        }
    }


    private Author getAuthor(AuthorRequestDto authorRequestDto) {
        return Author.builder()
                .name(authorRequestDto.getName())
                .age(authorRequestDto.getAge())
                .build();
    }

    private UserAuthentication getUserAuthentication(String email, String password, Author author) {
        return new UserAuthentication(email, passwordEncoder.encode(password), author);
    }
}
