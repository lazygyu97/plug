package com.sparta.plug.controller;

import com.sparta.plug.dto.PostResponseDto;
import com.sparta.plug.security.UserDetailsImpl;
import com.sparta.plug.service.PostService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final PostService postService;

    public HomeController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post-page")
    public String postPage(){
        return "post";
    }

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        //메인페이지에서 보여줄 게시글들 가져오기
        List<PostResponseDto> list = postService.getAllPosts().getPostsList();

        if (userDetails == null) {//로그인 x
            model.addAttribute("list",list);
            return "index";
        } else {//로그인 o
            model.addAttribute("nickname", userDetails.getNickname());
            model.addAttribute("list",list);
            return "index";
        }

    }


}