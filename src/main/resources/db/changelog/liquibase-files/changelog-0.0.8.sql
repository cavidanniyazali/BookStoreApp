ALTER TABLE authors
  ADD COLUMN role_id INT NOT NULL,
  ADD CONSTRAINT fk_authors_roles
                  FOREIGN KEY (role_id)
                  REFERENCES roles (id),
  ADD COLUMN user_authentication_id BIGINT NOT NULL,
  ADD CONSTRAINT fk_authors_user_authentication
                  FOREIGN KEY (user_authentication_id)
                  REFERENCES user_authentication(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;