package com.sparta.plug.controller;

import com.sparta.plug.dto.PostRequestDto;
import com.sparta.plug.dto.PostResponseDto;
import com.sparta.plug.security.UserDetailsImpl;
import com.sparta.plug.service.PostService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/plug")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @GetMapping("/post")
    public String postPage() {
        return "writePost";
    }
    @ResponseBody
    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return postService.createPost(requestDto, userDetails);
    }
}