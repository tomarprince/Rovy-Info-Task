/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tera.codingtest.api;

import com.google.gson.Gson;
import com.tera.codingtest.model.Comment;
import com.tera.codingtest.model.Post;
import com.tera.codingtest.model.Response;
import com.tera.codingtest.model.Todo;
import com.tera.codingtest.model.User;
import com.tera.codingtest.security.AuthRequest;
import com.tera.codingtest.security.JwtUtil;
import com.tera.codingtest.service.CommentService;
import com.tera.codingtest.service.PostService;
import com.tera.codingtest.service.TodoService;
import com.tera.codingtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private TodoService todoService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private Gson gson;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to User !!";
    }

    @PostMapping(value = "/get-token", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        Response response = new Response();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
            response.setMessage(jwtUtil.generateToken(authRequest.getUserName()));
        } catch (Exception ex) {
            response.setMessage("Invalid username/password");
        }
        return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(response));
    }

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addUser(@RequestBody User user) throws Exception {
        try {
            Response response = userService.addUser(user);
            return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(response));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gson.toJson(ex.getMessage()));
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getUserDetails(@PathVariable Long id) throws Exception {
        try {
            Response response = userService.findUserById(id);
            return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(response));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gson.toJson(ex.getMessage()));
        }
    }

    @PostMapping(value = "/{id}/posts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addPost(@RequestBody Post post, @PathVariable Long id) throws Exception {
        try {
            Response response = postService.addPost(post, id);
            return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(response));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gson.toJson(ex.getMessage()));
        }
    }

    @GetMapping(value = "/{id}/posts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPostDetails(@PathVariable Long id) throws Exception {
        try {
            Response response = postService.findPostByUserId(id);
            return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(response));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gson.toJson(ex.getMessage()));
        }
    }

    @PostMapping(value = "/{id}/todos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addTodos(@RequestBody Todo todo, @PathVariable Long id) throws Exception {
        try {
            Response response = todoService.addTodo(todo, id);
            return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(response));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gson.toJson(ex.getMessage()));
        }
    }

    @GetMapping(value = "/{id}/todos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getTodoDetails(@PathVariable Long id) throws Exception {
        try {
            Response response = todoService.findTodoByUserId(id);
            return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(response));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gson.toJson(ex.getMessage()));
        }
    }

    @PostMapping(value = "/{id}/posts/{postId}/comments/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addComment(@RequestBody Comment comment, @PathVariable Long id, @PathVariable Long postId) throws Exception {
        try {
            Response response = commentService.addComment(comment, id, postId);
            return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(response));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gson.toJson(ex.getMessage()));
        }
    }

    @GetMapping(value = "/{id}/posts/{postId}/comments/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getCommentDetails(@PathVariable Long id, @PathVariable Long postId) throws Exception {
        try {
            Response response = commentService.findCommentsByUserIdAndPostId(id, postId);
            return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(response));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gson.toJson(ex.getMessage()));
        }
    }
}
