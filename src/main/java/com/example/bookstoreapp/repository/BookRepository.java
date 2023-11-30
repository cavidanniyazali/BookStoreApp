package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.model.entity.Author;
import com.example.bookstoreapp.model.entity.Book;
import com.example.bookstoreapp.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> getBooksByAuthor(Author author);
}
