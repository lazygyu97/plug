package com.sparta.plug.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sparta.plug.dto.*;
import com.sparta.plug.entity.PlayList;
import com.sparta.plug.security.UserDetailsImpl;
import com.sparta.plug.service.CommentService;
import com.sparta.plug.service.PlayListService;
import com.sparta.plug.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.concurrent.RejectedExecutionException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {
// http:localhost:8080/api
    private final PostService postService;
    private final CommentService commentService;
    private final PlayListService playListService;
    @GetMapping("/post")
    public String postPage(){
        return "post";
    }

    @PostMapping("/post/create")
    public String createPost(@ModelAttribute PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Gson gson = new Gson();
        String imageUrl = postRequestDto.getSelectedTracks();
        List<PlayListRequestDto> selectedTracks = gson.fromJson(imageUrl, new TypeToken<List<PlayListRequestDto>>() {
        }.getType());
        postRequestDto.setImageUrl(selectedTracks.get(0).getImageUrl());
        postService.createPost(postRequestDto, userDetails.getUser());


        return "redirect:/";
    }

    @GetMapping("/post/{id}")
    public String getPost(@PathVariable Long id,Model model){

        PostResponseDto post=postService.getPost(id);

        List<PlayList> playList=playListService.getPlayList(id);

        List<CommentResponseDto> list = commentService.getComments(id).getCommentsList();

        model.addAttribute("postId",post.getId());
        model.addAttribute("post",post);
        model.addAttribute("tracks",playList);
        model.addAttribute("list",list);

        return "detailPost";
    }



    @PutMapping("/posts/{id}")
    public ResponseEntity<ApiResponseDto> updatePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        try {
            System.out.println(requestDto.getInfo());
            System.out.println(requestDto.getTitle());
            postService.updatePost(id, requestDto, userDetails.getUser());
            return ResponseEntity.ok().body(new ApiResponseDto("게시글 삭제 성공", HttpStatus.OK.value()));
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("작성자만 수정 할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }


    @DeleteMapping("/posts/{id}")
    public ResponseEntity<ApiResponseDto> deletePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) {
        try {
            postService.deletePost(id, userDetails.getUser());
            return ResponseEntity.ok().body(new ApiResponseDto("게시글 삭제 성공", HttpStatus.OK.value()));
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("작성자만 삭제 할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }
}
