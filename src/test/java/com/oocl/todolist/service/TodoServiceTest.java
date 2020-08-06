package com.oocl.todolist.service;

import com.oocl.todolist.dao.TodoRepository;
import com.oocl.todolist.dto.TodoRequest;
import com.oocl.todolist.exception.IllegalOperationException;
import com.oocl.todolist.exception.NoSuchDataException;
import com.oocl.todolist.mapper.TodoMapper;
import com.oocl.todolist.model.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


@SpringBootTest
class TodoServiceTest {

    private TodoRepository mockedTodoRepository = mock(TodoRepository.class);
    private TodoMapper mockedTodoMapper = mock(TodoMapper.class);
    private TodoService todoService = new TodoService(mockedTodoRepository, mockedTodoMapper);

    @Test
    void should_return_all_todo_when_getAllTodo_given_no_parameters() {
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
        Todo todo = new Todo("1", "hhh", false);
        TodoRequest todoRequest = new TodoRequest("1", "hhh", false);
        given(mockedTodoMapper.toTodo(todoRequest)).willReturn(todo);
        given(mockedTodoRepository.save(todo)).willReturn(todo);
        //when
        Todo saveTodo = todoService.addTodo(todoRequest);
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

//    @Test
//    void should_return_todo_when_update_todo_given_id_and_todo() {
//        //given
//        Todo todo = new Todo("1", "hhhh", true);
//        given(mockedTodoRepository.findById(todo.getId())).willReturn(Optional.of(todo));
//        Todo mockTodo = new Todo("1", "hhhh", false);
//        TodoRequest todoRequest = new TodoRequest("1", "hhhh", false);
//        given(mockedTodoRepository.save(mockTodo)).willReturn(mockTodo);
//        given(mockedTodoMapper.toTodoRequest(mockTodo)).willReturn(todoRequest);
//        //when
//        Todo actual = todoService.updateTodo(todo.getId(), todoRequest);
//        assertEquals(todo, actual);
//    }

    @Test
    void should_throw_no_such_data_exception_when_update_todo_given_id_and_todo() {
        //given
        TodoRequest todoRequest = new TodoRequest("1", "hhhh", true);
        given(mockedTodoRepository.findById("1")).willReturn(Optional.ofNullable(null));
        //when
        //then
        assertThrows(NoSuchDataException.class, () -> todoService.updateTodo("1", todoRequest));
    }

    @Test
    void should_throw_illegal_exception_update_company_given_company_Id_and_company_no_equal() {
        //given
        Todo todo = new Todo("1", "hhhh", false);
        TodoRequest updateTodo = new TodoRequest("2", "hhhh", false);
        //when
        assertThrows(IllegalOperationException.class, () -> todoService.updateTodo("1", updateTodo));
    }


}
