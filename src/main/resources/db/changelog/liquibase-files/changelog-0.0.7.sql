CREATE TABLE students_authors (
    student_id BIGINT REFERENCES students(id),
    author_id BIGINT REFERENCES authors(id),
    PRIMARY KEY (student_id, author_id)
);

ALTER TABLE students_authors
ADD CONSTRAINT fk_students_id
FOREIGN KEY (student_id) REFERENCES students(id);

ALTER TABLE students_authors
ADD CONSTRAINT fk_authors_id
FOREIGN KEY (author_id) REFERENCES authors(id);
