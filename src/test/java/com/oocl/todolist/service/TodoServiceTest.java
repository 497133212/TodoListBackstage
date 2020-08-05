package com.oocl.todolist.service;

import com.oocl.todolist.dao.TodoRepository;
import com.oocl.todolist.model.Todo;
import com.oocl.todolist.service.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;


@SpringBootTest
public class TodoServiceTest {

    TodoRepository mockedTodoRepository = mock(TodoRepository.class);
    TodoService todoService = new TodoService(mockedTodoRepository);

    @Test
    void shoule_return_all_todo_when_getAllTodo_given_no_parameters() {
        //given
        List<Todo> todos = new ArrayList<>();
        todos.add(new Todo("1", "hhh", true));
        todos.add(new Todo("2", "sss",false));
        given(mockedTodoRepository.findAll()).willReturn(todos);
        //when
        List<Todo> todoList = todoService.getAllTodo();
        //then
        assertEquals(todos, todoList);
    }
}
