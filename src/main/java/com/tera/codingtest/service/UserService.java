/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tera.codingtest.service;

import com.tera.codingtest.model.Response;
import com.tera.codingtest.model.User;
import com.tera.codingtest.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Prince
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Response addUser(User user) {
        Response response = new Response();
        if (user != null) {
            User userDetails = userRepository.save(user);
            response.setMessage("User Added Successfully, UserId - " + userDetails.getId());
            response.setResponse("SUCCESS");
        } else {
            response.setResponse("FAILURE");
            response.setMessage("Operation UnSuccessful");
        }
        return response;
    }

    public Response findUserById(Long id) {
        Response response = new Response();
        if (id != null) {
            Optional<User> userDetails = userRepository.findById(id);
            response.setMessage("User Details are - " + userDetails.toString());
            response.setResponse("SUCCESS");
        } else {
            response.setResponse("FAILURE");
            response.setMessage("Operation UnSuccessful");
        }
        return response;
    }

    public Response findAllUsers() {
        Response response = new Response();
        List<User> userDetails = userRepository.findAll();
        response.setMessage("User Details are - " + userDetails.toString());
        response.setResponse("SUCCESS");
        return response;
    }
}
