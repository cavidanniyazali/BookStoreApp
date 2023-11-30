CREATE TABLE roles (
    id INT PRIMARY KEY,
    name VARCHAR(20)
);

INSERT INTO roles (id, name)
VALUES
    (1, 'ADMIN'),
    (2, 'STUDENT'),
    (3, 'AUTHOR'),
    (4, 'GUEST');
