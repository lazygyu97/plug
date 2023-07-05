package com.sparta.plug.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sparta.plug.dto.CommentResponseDto;
import com.sparta.plug.dto.PostRequestDto;
import com.sparta.plug.dto.PostResponseDto;
import com.sparta.plug.security.UserDetailsImpl;
import com.sparta.plug.service.CommentService;
import com.sparta.plug.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {
    private final PostService postService;
    private final CommentService commentService;

    @GetMapping("/post")
    public String postPage(){
        return "post";
    }

    @PostMapping("/post/create")
    public String createPost(@ModelAttribute PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String selectedTracksJson = postRequestDto.getSelectedTracks();
        Gson gson = new Gson();

        List<PostRequestDto.Track> selectedTracks = gson.fromJson(selectedTracksJson, new TypeToken<List<PostRequestDto.Track>>(){}.getType());
        postRequestDto.setTrackList(selectedTracks);
        System.out.println("selectedTracks.get(0) = " + selectedTracks.get(0).getArtist());
        postService.createPost(postRequestDto, userDetails.getUser());

        return "redirect:/";
    }

    @GetMapping("/post/{id}")
    public String getPost(@PathVariable Long id,Model model){
        PostResponseDto post=postService.getPost(id);
        List<CommentResponseDto> list = commentService.getComments(id).getCommentsList();
        model.addAttribute("post",post);
        model.addAttribute("list",list);

        return "detailPost";
    }
}
