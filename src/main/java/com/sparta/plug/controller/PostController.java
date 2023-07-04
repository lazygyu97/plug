package com.sparta.plug.controller;

import com.sparta.plug.dto.PostRequestDto;
import com.sparta.plug.security.UserDetailsImpl;
import com.sparta.plug.service.PostService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/post/create")
    public String createPost(@ModelAttribute PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        System.out.println("Title: " + postRequestDto.getTitle());
        System.out.println("Info: " + postRequestDto.getInfo());
        System.out.println("Image URL: " + postRequestDto.getImageUrl());
        postService.createPost(postRequestDto, userDetails.getUser());
        return "redirect:/";
    }
}
