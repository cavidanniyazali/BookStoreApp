CREATE TABLE IF NOT EXISTS user_authentication (
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  email VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(100) NOT NULL,
  student_id BIGINT,
  CONSTRAINT fk_user_authentication_student_id
        FOREIGN KEY (student_id)
        REFERENCES students(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  author_id BIGINT,
  CONSTRAINT fk_author_authentication_student_id
        FOREIGN KEY (author_id)
        REFERENCES authors(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);