package com.oocl.todolist.service;

import com.oocl.todolist.dao.TodoRepository;
import com.oocl.todolist.dto.TodoRequest;
import com.oocl.todolist.exception.IllegalOperationException;
import com.oocl.todolist.exception.NoSuchDataException;
import com.oocl.todolist.mapper.TodoMapper;
import com.oocl.todolist.model.Todo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TodoService {

    private TodoRepository todoRepository;
    private TodoMapper todoMapper;

    public TodoService(TodoRepository todoRepository, TodoMapper todoMapper) {
        this.todoMapper = todoMapper;
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAllTodo() {
        return todoRepository.findAll();
    }

    public Todo updateTodo(String id, TodoRequest todoRequest) {
        if(!id.equals(todoRequest.getId())) {
            throw new IllegalOperationException();
        }
        Todo updateTodo = todoRepository.findById(id).orElseThrow(NoSuchDataException::new);
        updateTodo.setStatus(!updateTodo.isStatus());
        return todoRepository.save(updateTodo);
    }

    public Todo addTodo(TodoRequest todoRequest) {
        if(Objects.nonNull(todoRequest)) {
            return todoRepository.save(todoMapper.toTodo(todoRequest));
        }
        return null;
    }

    public void deleteTodo(String id) {
        Todo todo = todoRepository.findById(id).orElse(null);
        if(Objects.isNull(todo)) {
            throw new NoSuchDataException();
        }
        todoRepository.deleteById(id);
    }
}
