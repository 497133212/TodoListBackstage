package com.oocl.todolist.service;

import com.oocl.todolist.common.JsonResult;
import com.oocl.todolist.dao.TodoRepository;
import com.oocl.todolist.dto.TodoResponse;
import com.oocl.todolist.model.Todo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodo() {
        return todoRepository.findAll();
    }

    public Todo updateTodo(String id) {
        return null;
    }

    public Todo addTodo(Todo todo) {
        return null;
    }

    public void deleteTodo(String id) {
    }
}
