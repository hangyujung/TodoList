package com.gyu.todolist.controller;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gyu.todolist.domain.Todo;
import com.gyu.todolist.service.TodoService;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
    long num=0;

    @Autowired
    private TodoService todoService;

    /*
     *     목록 조회
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTodos() throws Exception {
        List<Todo> todos = todoService.getTodos(Sort.by(Direction.ASC, "id"));
        return ResponseEntity.ok(todos);
    }

    /*
     *     등록
     */
    @PostMapping
    public ResponseEntity<String> postTodo(@RequestBody Todo todo) throws Exception {

        System.out.println(todo.toString());

        todo.setCreatedDateTime(LocalDateTime.now());
        todo.setIsComplete(false);


        todoService.postTodo(todo);
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    /*
     *     수정
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> putTodo(@PathVariable("id") Long id) throws Exception {
        Todo todo = todoService.findTodoById(id);

        Boolean isComplete = todo.getIsComplete() ? false : true;
        todo.setIsComplete(isComplete);
        todoService.postTodo(todo);

        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    /*
     *     삭제
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id) throws Exception {
        todoService.deleteTodo(id);

        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

}