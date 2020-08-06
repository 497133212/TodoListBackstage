package com.oocl.todolist.model;

import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;


@Entity
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String content;
    private boolean status;

    public Todo() {
    }

    public Todo(String id, String content, boolean status) {
        this.id = id;
        this.content = content;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
