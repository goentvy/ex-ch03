SHOW DATABASES;
CREATE DATABASE todolist_db;
USE todolist_db;

SHOW TABLES;

CREATE TABLE todos (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255),
                       completed BOOLEAN
);

SELECT * FROM todos;