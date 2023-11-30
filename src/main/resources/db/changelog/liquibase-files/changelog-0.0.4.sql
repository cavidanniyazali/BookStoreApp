CREATE TABLE IF NOT EXISTS students_books (
    student_id BIGINT REFERENCES students(id),
    book_id BIGINT REFERENCES books(id),
    PRIMARY KEY (student_id, book_id)
);

ALTER TABLE students_books
ADD CONSTRAINT fk_student_id
FOREIGN KEY (student_id) REFERENCES students(id);

ALTER TABLE students_books
ADD CONSTRAINT fk_book_id
FOREIGN KEY (book_id) REFERENCES books(id);
