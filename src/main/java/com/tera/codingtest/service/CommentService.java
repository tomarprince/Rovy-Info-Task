/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tera.codingtest.service;

import com.tera.codingtest.model.Response;
import com.tera.codingtest.model.Comment;
import com.tera.codingtest.repository.CommentRepository;
import com.tera.codingtest.repository.TodoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Prince
 */
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Response addComment(Comment comment, Long userId, Long postId) {
        Response response = new Response();
        if (comment != null) {
            comment.setUserId(userId);
            comment.setPostId(postId);
            Comment commentDetails = commentRepository.save(comment);
            response.setMessage("Comment Added Successfully, CommentId - " + commentDetails.getId());
            response.setResponse("SUCCESS");
        } else {
            response.setResponse("FAILURE");
            response.setMessage("Operation UnSuccessful");
        }
        return response;
    }

    public Response findCommentsByUserIdAndPostId(Long userId, Long postId) {
        Response response = new Response();
        if (userId != null & postId != null) {
            List<Comment> commentDetails = commentRepository.findByUserIdAndPostId(userId, postId);
            response.setMessage("Comment Details are - " + commentDetails.toString());
            response.setResponse("SUCCESS");
        } else {
            response.setResponse("FAILURE");
            response.setMessage("Operation UnSuccessful");
        }
        return response;
    }

    public Response findAllComments() {
        Response response = new Response();
        List<Comment> commentDetails = commentRepository.findAll();
        response.setMessage("Comment Details are - " + commentDetails.toString());
        response.setResponse("SUCCESS");
        return response;
    }
}
