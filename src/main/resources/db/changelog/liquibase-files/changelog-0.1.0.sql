CREATE TABLE IF NOT EXISTS verification_token (
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  token VARCHAR(300),
  created_date DATE,
  expired_date DATE,
  user_authentication_id BIGINT NOT NULL,
  CONSTRAINT fk_user_authentication_verification_token
    FOREIGN KEY (user_authentication_id)
    REFERENCES user_authentication(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);