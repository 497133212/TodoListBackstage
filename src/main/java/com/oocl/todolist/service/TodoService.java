package com.oocl.todolist.service;

import com.oocl.todolist.common.JsonResult;
import com.oocl.todolist.dao.TodoRepository;
import com.oocl.todolist.dto.TodoRequest;
import com.oocl.todolist.dto.TodoResponse;
import com.oocl.todolist.mapper.TodoMapper;
import com.oocl.todolist.model.Todo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TodoService {

    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodo() {
        return todoRepository.findAll();
    }

    public Todo updateTodo(String id, TodoRequest todoRequest) {
        return null;
    }

    public Todo addTodo(TodoRequest todoRequest) {
        if(Objects.nonNull(todoRequest)) {
            return todoRepository.save(TodoMapper.toTodo(todoRequest));
        }
        return null;
    }

    public void deleteTodo(String id) {
    }
}
