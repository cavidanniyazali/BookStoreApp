package com.example.bookstoreapp.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@Table(name = "authors")
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Exclude
    Long id;

    @Column
    String name;

    @Column
    int age;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    List<Book> books;

    @ManyToMany(mappedBy = "subscriptions", fetch = FetchType.EAGER)
    @ToString.Exclude
    List<Student> followers;

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @ToString.Exclude
    Role role;

    @OneToOne(cascade = CascadeType.ALL)//
    @JoinColumn(name = "user_authentication_id", referencedColumnName = "id")
    @ToString.Exclude
    UserAuthentication userAuthentication;
}
