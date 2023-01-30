package com.farvic.todoapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
// import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

// @EnableAutoConfiguration(exclude = { MongoAutoConfiguration.class })
@SpringBootApplication
public class TodoappSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoappSpringApplication.class, args);
	}

}
