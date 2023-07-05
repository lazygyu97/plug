package com.sparta.plug.controller;

import com.sparta.plug.dto.CommentRequestDto;
import com.sparta.plug.dto.CommentResponseDto;
import com.sparta.plug.security.UserDetailsImpl;
import com.sparta.plug.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment")
    public String createComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @ModelAttribute CommentRequestDto requestDto) {
        commentService.createComment(requestDto, userDetails.getUser());
        String id= String.valueOf(requestDto.getPostId());
        return "redirect:/api/post/"+id;
    }

    @PutMapping("/comment/{id}")
    public String updateComment(@AuthenticationPrincipal UserDetailsImpl userDetails, )

}
