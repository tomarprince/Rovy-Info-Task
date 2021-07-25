/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tera.codingtest.service;

import com.tera.codingtest.model.Response;
import com.tera.codingtest.model.Todo;
import com.tera.codingtest.repository.TodoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Prince
 */
@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public Response addTodo(Todo todo, Long userId) {
        Response response = new Response();
        if (todo != null) {
            todo.setUserId(userId);
            Todo todoDetails = todoRepository.save(todo);
            response.setMessage("Todo Added Successfully, TodoId - " + todoDetails.getId());
            response.setResponse("SUCCESS");
        } else {
            response.setResponse("FAILURE");
            response.setMessage("Operation UnSuccessful");
        }
        return response;
    }

    public Response findTodoByUserId(Long userId) {
        Response response = new Response();
        if (userId != null) {
            List<Todo> postDetails = todoRepository.findByUserId(userId);
            response.setMessage("Todo Details are - " + postDetails.toString());
            response.setResponse("SUCCESS");
        } else {
            response.setResponse("FAILURE");
            response.setMessage("Operation UnSuccessful");
        }
        return response;
    }

    public Response findAllTodos() {
        Response response = new Response();
        List<Todo> postDetails = todoRepository.findAll();
        response.setMessage("Todo Details are - " + postDetails.toString());
        response.setResponse("SUCCESS");
        return response;
    }
}
