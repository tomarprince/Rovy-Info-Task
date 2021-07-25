/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tera.codingtest.service;

import com.tera.codingtest.model.Response;
import com.tera.codingtest.model.Post;
import com.tera.codingtest.repository.PostRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Prince
 */
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Response addPost(Post post, Long userId) {
        Response response = new Response();
        if (post != null) {
            post.setUserId(userId);
            Post postDetails = postRepository.save(post);
            response.setMessage("Post Added Successfully, PostId - " + postDetails.getId());
            response.setResponse("SUCCESS");
        } else {
            response.setResponse("FAILURE");
            response.setMessage("Operation UnSuccessful");
        }
        return response;
    }

    public Response findPostByUserId(Long userId) {
        Response response = new Response();
        if (userId != null) {
            List<Post> postDetails = postRepository.findByUserId(userId);
            response.setMessage("Post Details are - " + postDetails.toString());
            response.setResponse("SUCCESS");
        } else {
            response.setResponse("FAILURE");
            response.setMessage("Operation UnSuccessful");
        }
        return response;
    }

    public Response findAllPosts() {
        Response response = new Response();
        List<Post> postDetails = postRepository.findAll();
        response.setMessage("Post Details are - " + postDetails.toString());
        response.setResponse("SUCCESS");
        return response;
    }
}
