package com.oocl.todolist.service;

import com.oocl.todolist.common.JsonResult;
import com.oocl.todolist.dao.TodoRepository;
import com.oocl.todolist.dto.TodoRequest;
import com.oocl.todolist.dto.TodoResponse;
import com.oocl.todolist.exception.IllegalOperationException;
import com.oocl.todolist.exception.NoSuchDataException;
import com.oocl.todolist.mapper.TodoMapper;
import com.oocl.todolist.model.Todo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
        if(!id.equals(todoRequest.getId())) {
            throw new IllegalOperationException();
        }
        Todo todo = TodoMapper.toTodo(todoRequest);
        Todo updateTodo = todoRepository.findById(id).orElseThrow(NoSuchDataException::new);
        BeanUtils.copyProperties(todo, updateTodo);
        return todoRepository.save(updateTodo);
    }

    public Todo addTodo(TodoRequest todoRequest) {
        if(Objects.nonNull(todoRequest)) {
            return todoRepository.save(TodoMapper.toTodo(todoRequest));
        }
        return null;
    }

    public void deleteTodo(String id) {
        if(!StringUtils.isEmpty(id)) {
            todoRepository.deleteById(id);
        }
    }
}
