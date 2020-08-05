package com.oocl.todolist.service;

import com.oocl.todolist.common.JsonResult;
import com.oocl.todolist.dao.TodoRepository;
import com.oocl.todolist.dto.TodoResponse;
import com.oocl.todolist.model.Todo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    public TodoService(TodoRepository todoRepository) {

    }

    public List<Todo> getAllTodo() {
        return null;
    }

    public TodoResponse updateTodo(String id) {
        return null;
    }

    public TodoResponse addTodo(Todo todo) {
        return null;
    }

    public void deleteTodo(String id) {
    }
}
