package com.sparta.plug.controller;

import com.sparta.plug.dto.PostResponseDto;
import com.sparta.plug.security.UserDetailsImpl;
import com.sparta.plug.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostService postService;

    @GetMapping("/post-page")
    public String postPage(){
        return "post";
    }

    @GetMapping("/")                                      //로그인 성공 한 유저의 정보를 갖고 있는게
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        //model ->
        //메인페이지에서 보여줄 게시글들 가져오기
        List<PostResponseDto> list = postService.getAllPosts().getPostsList();
        Collections.reverse(list);
        if (userDetails == null) {//로그인 x
            model.addAttribute("list",list);
            return "index";
        } else {//로그인 o
            model.addAttribute("nickname", userDetails.getNickname());
            model.addAttribute("list",list);
            return "index";
        }

    }
    @GetMapping("/{keyword}")
    public String searchPost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable String keyword, Model model){
        List<PostResponseDto> post = postService.searchPost(keyword);
        if(post==null){
            return "index";
        }
        Collections.reverse(post);
        if (userDetails == null) {//로그인 x
            model.addAttribute("list",post);
            return "index";
        } else {//로그인 o
            model.addAttribute("nickname", userDetails.getNickname());
            model.addAttribute("list",post);
            return "index";
        }

    }



}