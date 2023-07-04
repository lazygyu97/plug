package com.sparta.plug.controller;

import com.sparta.plug.dto.PostRequestDto;
import com.sparta.plug.dto.PostResponseDto;
import com.sparta.plug.entity.Post;
import com.sparta.plug.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        Post post = postService.createPost(requestDto);
        return new PostResponseDto(post);
    }

    @GetMapping("/posts")
    public List<PostResponseDto> readAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return posts.stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/post/{id}")
    public PostResponseDto readPost(@PathVariable Long id) {
        Post post = postService.getPost(id);
        return new PostResponseDto(post);
    }

    @PutMapping("/post/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        Post post = postService.updatePost(id, requestDto);
        return new PostResponseDto(post);
    }

    @DeleteMapping("/post/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}

