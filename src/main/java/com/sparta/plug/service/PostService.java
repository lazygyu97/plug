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

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
    public List<Post> getAllPosts() {

        return null;
    }


    public PostResponseDto createPost(PostRequestDto requestDto, UserDetailsImpl userDetails){
        Post post = new Post(requestDto, userDetails); //타임스탬프가 안찍힘

        Post savePost = postRepository.save(post);

        PostResponseDto postResponseDto = new PostResponseDto(savePost);

        //userList에 포스트 추가
        userDetails.getUser().addPostList(savePost);

        return postResponseDto;
    }

}
