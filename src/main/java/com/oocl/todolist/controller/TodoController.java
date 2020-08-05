package com.oocl.todolist.controller;

import com.oocl.todolist.common.JsonResult;
import com.oocl.todolist.dto.TodoResponse;
import com.oocl.todolist.mapper.TodoMapper;
import com.oocl.todolist.model.Todo;
import com.oocl.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.oocl.todolist.common.JsonResult.success;

@RestController
@RequestMapping("/todos")
public class TodoController {

    TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public JsonResult<List<Todo>> getAllTodo() {
        return success(todoService.getAllTodo());
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TodoResponse updateTodo(@PathVariable String id) {
        return TodoMapper.toTodoResponse(todoService.updateTodo(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse addTodo(@RequestBody Todo todo) {
        return TodoMapper.toTodoResponse(todoService.addTodo(todo));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTodo(@PathVariable String id) {
        todoService.deleteTodo(id);
    }

}
