package com.entvy.todolist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    private Long id;
    private String title;
    private boolean completed;
}
