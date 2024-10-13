package com.example.todolist.controller;

import com.example.todolist.model.Todo;
import com.example.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping
    public List<Todo> getAllTodos(){
        return todoService.getAllTodo();
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo){
        return todoService.createTodo(todo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id){
        return todoService.getTodoById(id)
                .map(todo -> ResponseEntity.ok().body(todo))
                .orElse(ResponseEntity.notFound().build());

    }

    @PostMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todo){
        return todoService.getTodoById(id)
                .map(todo1 -> {
                    todo1.setTitle(todo.getTitle());
                    todo1.setCompleted(todo.isCompleted());
                    Todo updatedTodo = todoService.updateTodo(todo1);
                    return ResponseEntity.ok().body(updatedTodo);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id){
        return todoService.getTodoById(id)
                .map(todos->{
                    try {
                        todoService.deleteTodo(id);
                        return ResponseEntity.ok("The item is deletedd");
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }).orElse(ResponseEntity.notFound().build());
    }

}
