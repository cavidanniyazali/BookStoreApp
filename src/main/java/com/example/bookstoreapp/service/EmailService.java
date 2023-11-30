package com.example.bookstoreapp.service;

import com.example.bookstoreapp.model.entity.Author;
import com.example.bookstoreapp.model.entity.Book;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {
    public void createEmail(String to, String subject, String body);

    String notifySubscribedStudentsWithEmail(Author author, Book newbook);
}
