package com.sparta.plug.controller;

import com.sparta.plug.security.UserDetailsImpl;
import com.sparta.plug.service.PostService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

//    private final PostService postService;
//
//    public HomeController(PostService postService) {
//        this.postService = postService;
//    }


    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {

//        postService.getAllPosts();

        if (userDetails == null) {
            return "index";
        } else {
            model.addAttribute("username", userDetails.getUsername());
            return "index";
        }

    }

    @GetMapping("/posts/new")
    public String createPostPage() {
        return "post-create";
    }

    @GetMapping("/posts")
    public String readAllPostsPage() {
        return "post-list";
    }

    @GetMapping("/posts/{id}")
    public String readPostPage(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "post-read";
    }

    @GetMapping("/posts/{id}/edit")
    public String updatePostPage(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "post-update";
    }

    @GetMapping("/posts/{id}/delete")
    public String deletePostPage(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "post-delete";
    }


}