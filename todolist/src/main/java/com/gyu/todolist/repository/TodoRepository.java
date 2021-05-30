package com.gyu.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gyu.todolist.domain.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}