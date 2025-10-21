SHOW DATABASES;
CREATE DATABASE student_db;
USE student_db;

SHOW TABLES;

CREATE TABLE students (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          email VARCHAR(100) NOT NULL,
                          age INT,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

SELECT * FROM students;

INSERT INTO students (name, email, age, created_at, updated_at)
VALUES ('이주환', 'juhwan.lee@example.com', 22, NOW(), NOW()),
       ('박근철', 'park@example.com', 22, NOW(), NOW()),
       ('칸코쿠', 'koku.kan@example.com', 24, NOW(), NOW());