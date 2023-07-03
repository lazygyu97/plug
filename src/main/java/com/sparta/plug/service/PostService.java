package com.sparta.plug.service;

import com.sparta.plug.entity.Post;
import com.sparta.plug.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    
    private final PostRepository postRepository;


    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    public List<Post> getAllPosts() {

        return null;
    }
}
