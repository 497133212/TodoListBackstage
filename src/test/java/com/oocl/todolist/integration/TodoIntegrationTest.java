package com.oocl.todolist.integration;

import com.alibaba.fastjson.JSON;
import com.oocl.todolist.dao.TodoRepository;
import com.oocl.todolist.model.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TodoRepository todoRepository;

    @BeforeEach
    void tearDown() {
        todoRepository.deleteAll();
    }

    @Test
    void should_return_todo_list_when_get_all_todo_list_given_request() throws Exception {
        //given
        List<Todo> todos = new ArrayList<>();
        todos.add(new Todo("1","hhhh",true));
        todos.add(new Todo("2","ssss",false));
        List<Todo> todoList = todoRepository.saveAll(todos);
        //then
        mockMvc.perform(get("/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.msg").value("succeed"))
                .andExpect(jsonPath("$.data[0].id").value(todoList.get(0).getId()));
    }

    @Test
    void should_return_todo_when_add_todo_given_todo() throws Exception {
        //given
        Todo todo = todoRepository.save(new Todo("1", "hhh", true));
        String json = JSON.toJSONString(todo);
        //when
        mockMvc.perform(post("/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(todo.getId()))
                .andExpect(jsonPath("$.content").value(todo.getContent()))
                .andExpect(jsonPath("$.status").value(todo.isStatus()));
        //then

    }

    @Test
    void should_return_todo_when_update_todo_given_todo() throws Exception {
        //given
        Todo todo = todoRepository.save(new Todo("1", "hhh", true));
        Todo updateTodo = new Todo("1","sss",false);
        String json = JSON.toJSONString(updateTodo);
        //when
        mockMvc.perform(put("/todos/" + updateTodo.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(jsonPath("$.id").value(updateTodo.getId()))
                .andExpect(jsonPath("$.content").value(updateTodo.getContent()))
                .andExpect(jsonPath("$.status").value(updateTodo.isStatus()));
        //then
    }

    @Test
    void should_delete_todo_when_delete_todo_by_id_given_id() throws Exception {
        //given
        Todo todo = todoRepository.save(new Todo("1","hhh",true));
        //when
        mockMvc.perform(delete("/todos/" + todo.getId())).andExpect(status().isOk());
        //then
        assertFalse(todoRepository.findById(todo.getId()).isPresent());
    }
}
