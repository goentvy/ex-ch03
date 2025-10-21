SHOW DATABASES;
CREATE DATABASE crud_db;
USE crud_db;

SHOW TABLES;

CREATE TABLE student(
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        NAME VARCHAR(100) NOT NULL,
                        email VARCHAR(100),
                        age INT,
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
SELECT * FROM student;
-- 샘플데이터
INSERT INTO student(NAME, email, age) VALUES
                                          ('김철수', 'kim@example.com', 20),
                                          ('이영희', 'kim@example.com', 22),
                                          ('박민수', 'kim@example.com', 21);