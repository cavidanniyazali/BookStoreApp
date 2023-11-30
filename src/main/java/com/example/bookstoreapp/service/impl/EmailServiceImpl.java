package com.example.bookstoreapp.service.impl;

import com.example.bookstoreapp.model.entity.Author;
import com.example.bookstoreapp.model.entity.Book;
import com.example.bookstoreapp.model.entity.Student;
import com.example.bookstoreapp.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;

    @Async
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

    public void createEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        sendEmail(message);
    }

    @Override
    public String notifySubscribedStudentsWithEmail(Author author, Book newbook) {
        List<Student> subscribedStudents = author.getFollowers();
        for (Student student : subscribedStudents) {
            String email = student.getUserAuthentication().getEmail();
            createEmail(email, "New Book Notification", newbook.toString());
        }
        return "Email sent successfully";
    }
}
