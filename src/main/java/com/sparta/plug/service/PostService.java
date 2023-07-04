package com.sparta.plug.service;

import com.sparta.plug.dto.PostRequestDto;
import com.sparta.plug.dto.PostResponseDto;
import com.sparta.plug.entity.Post;
import com.sparta.plug.entity.User;
import com.sparta.plug.repository.PostRepository;
import com.sparta.plug.security.UserDetailsImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    
    private final PostRepository postRepository;

    public PostResponseDto createPost(PostRequestDto requestDto, UserDetailsImpl userDetails){
        Post post = new Post(requestDto, userDetails);

        Post savePost = postRepository.save(post);

        PostResponseDto postResponseDto = new PostResponseDto(savePost);

        //userList에 포스트 추가
        userDetails.getUser().getPostList().add(savePost);

        return postResponseDto;
    }

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    public List<Post> getAllPosts() {

        return null;
    }
}
