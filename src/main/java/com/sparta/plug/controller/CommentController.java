package com.sparta.plug.controller;

import com.sparta.plug.dto.ApiResponseDto;
import com.sparta.plug.dto.CommentRequestDto;
import com.sparta.plug.dto.CommentResponseDto;
import com.sparta.plug.entity.Comment;
import com.sparta.plug.security.UserDetailsImpl;
import com.sparta.plug.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.RejectedExecutionException;

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
    public ResponseEntity<ApiResponseDto> updateComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id, @RequestBody CommentRequestDto requestDto) {

        try { // commentService.updateComment 메서드를 호출하여 댓글 수정 -> 예외가 발생하면 catch 블록으로 이동하여 예외를 처리
            CommentResponseDto comment = commentService.updateComment(id, requestDto, userDetails.getUser()); // commentServiced의 updateComment(수정)을 실행하여 id, requestDto, userDetails.getUser()를 수정
            // commentService의 updateComment는 예외 발생 할 수 있다
            return ResponseEntity.ok().body(comment);
        } catch (RejectedExecutionException exception) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("작성자만 삭제 할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<ApiResponseDto> deleteComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) {
        try {
            commentService.deleteComment(id, userDetails.getUser()); // commentServiced의 deleteComment(삭제)을 실행하여 id, requestDto, userDetails.getUser()를 삭제
            return ResponseEntity.ok().body(new ApiResponseDto("댓글 삭제 성공", HttpStatus.OK.value()));
        } catch (RejectedExecutionException exception) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("작성자만 삭제 할 수 있습니다.", HttpStatus.BAD_REQUEST.value()));
        }
    }
}
