package com.farvic.todoapp.model;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;
// import jakarta.persistence.Entity;
import org.springframework.stereotype.Component;

// @Entity
@Component
@Document(collection = "todos")
public class Todo {
    @Id
    private String id;

    private String title;
    private String description;
    private boolean published;

    public Todo(String title, String description, boolean published) {
        this.title = title;
        this.description = description;
        this.published = published;

    }

    public Todo() {

    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean isPublished) {
        this.published = isPublished;
    }

    @Override
    public String toString() {
        return "Tutorial [id=" + id + ", title=" + title + ", desc=" + description + ", published=" + published + "]";
    }
}
