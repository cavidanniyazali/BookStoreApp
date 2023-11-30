CREATE TABLE IF NOT EXISTS books (
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  name VARCHAR(20) NOT NULL,
  author_id BIGINT NOT NULL,
    CONSTRAINT fk_books_authors
          FOREIGN KEY (author_id)
          REFERENCES authors (id)
);