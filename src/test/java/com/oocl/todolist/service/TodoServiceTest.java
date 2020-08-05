package com.oocl.todolist.service;

import com.oocl.todolist.dao.TodoRepository;
import com.oocl.todolist.mapper.TodoMapper;
import com.oocl.todolist.model.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


@SpringBootTest
class TodoServiceTest {

    private TodoRepository mockedTodoRepository = mock(TodoRepository.class);
    private TodoService todoService = new TodoService(mockedTodoRepository);

    @Test
    void shoule_return_all_todo_when_getAllTodo_given_no_parameters() {
        //given
        List<Todo> todos = new ArrayList<>();
        todos.add(new Todo("1", "hhh", true));
        todos.add(new Todo("2", "sss", false));
        given(mockedTodoRepository.findAll()).willReturn(todos);
        //when
        List<Todo> todoList = todoService.getAllTodo();
        //then
        assertEquals(todos, todoList);
    }

    @Test
    void should_return_todo_when_add_todo_given_todo() {
        //given
        Todo todo = new Todo("1", "hhh",false);
        given(mockedTodoRepository.save(todo)).willReturn(todo);
        //when
        Todo saveTodo = todoService.addTodo(TodoMapper.toTodoRequest(todo));
        //then
        assertEquals(todo, saveTodo);
    }

    @Test
    void should_return_null_when_add_todo_given_null() {
        //given
        given(mockedTodoRepository.save(null)).willReturn(null);
        //when
        Todo todo = todoService.addTodo(null);
        //then
        assertNull(todo);
    }

    @Test
    void should_delete_todo_when_delete_todo_by_id_given_id() {
        //given
        //when
        String id = "1";
        todoService.deleteTodo(id);
        //then
        verify(mockedTodoRepository).deleteById(id);
    }

    @Test
    void should_return_todo_when_update_todo_given_id_and_todo() {
        //given
        Todo todo = new Todo("1", "hhhh",true);
        Todo updateTodo = new Todo("1", "sssss", false);
        given(mockedTodoRepository.findById(todo.getId())).willReturn(Optional.of(todo));
        given(mockedTodoRepository.save(updateTodo)).willReturn(updateTodo);
        //when
        Todo actual = todoService.updateTodo("1", TodoMapper.toTodoRequest(updateTodo));
        assertEquals(updateTodo, actual);
    }


}
