package com.farvic.todoapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.farvic.todoapp.model.Todo;

public interface TodoRepository extends MongoRepository<Todo, String> {
    List<Todo> findByTitleContaining(String title);

    List<Todo> findByPublished(boolean published);
}
