package com.sparta.plug.service;

import com.sparta.plug.dto.PostListResponseDto;
import com.sparta.plug.dto.PostRequestDto;
import com.sparta.plug.dto.PostResponseDto;
import com.sparta.plug.entity.Post;
import com.sparta.plug.entity.User;
import com.sparta.plug.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;


    public PostListResponseDto getAllPosts() {
        List<PostResponseDto> postList = postRepository.findAll().stream().map(PostResponseDto::new).collect(Collectors.toList());

        return new PostListResponseDto(postList);
    }

    public PostResponseDto createPost(PostRequestDto postRequestDto, User user) {
        Post post = new Post(postRequestDto, user);
        post.setUser(user);
        postRepository.save(post);
        return new PostResponseDto(post);
    }

    public PostResponseDto getPost(Long id) {
        Post post = findPost(id);
        return new PostResponseDto(post);
    }

    public Post findPost(long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("선택한 게시글은 존재하지 않습니다."));
    }
}
