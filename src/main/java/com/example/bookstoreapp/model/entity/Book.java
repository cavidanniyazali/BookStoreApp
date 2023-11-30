package com.example.bookstoreapp.model.entity;

import javax.persistence.*;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@Table(name = "books")
@AllArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Exclude
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToMany(mappedBy = "readingBooks", fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<Student> readers;
}
