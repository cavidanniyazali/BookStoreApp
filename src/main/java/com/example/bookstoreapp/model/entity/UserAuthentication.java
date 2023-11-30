package com.example.bookstoreapp.model.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity(name = "user_authentication")
@Getter
@Setter
@NoArgsConstructor
public class UserAuthentication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;

    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @OneToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @OneToOne(mappedBy = "userAuthentication")
    private VerificationToken verificationToken;

    public UserAuthentication(String email, String password, Student student) {
        this.email = email;
        this.password = password;
        this.student = student;
    }

    public UserAuthentication(String email, String password, Author author) {
        this.email = email;
        this.password = password;
        this.author = author;
    }
}
