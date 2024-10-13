package com.example.todolist.service;

import com.example.todolist.model.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    public List<Todo> getAllTodo();
    public Optional<Todo> getTodoById(long id);
    public Todo createTodo(Todo todo);
    public Todo updateTodo(Todo todo);
    public void deleteTodo(long id) throws Exception;
}
