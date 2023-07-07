package com.sparta.plug.service;



import com.sparta.plug.dto.*;
import com.sparta.plug.entity.Comment;
import com.sparta.plug.entity.Post;
import com.sparta.plug.entity.User;
import com.sparta.plug.entity.UserRoleEnum;
import com.sparta.plug.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CommentService {
    private final PostService postService;
    private final CommentRepository commentRepository;

    public CommentListResponseDto getComments(Long id) {
        List<CommentResponseDto> commentsList = commentRepository.findByPostId(id).stream().map(CommentResponseDto::new).collect(Collectors.toList());

        return new CommentListResponseDto(commentsList);
    }
    public CommentResponseDto createComment(CommentRequestDto requestDto, User user) {
        Post post = postService.findPost(requestDto.getPostId());
        Comment comment = new Comment(requestDto.getBody());
        comment.setUser(user);
        comment.setPost(post);

        var savedComment = commentRepository.save(comment);

        return new CommentResponseDto(savedComment);
    }
    @Transactional
    public CommentResponseDto updateComment(Long id, CommentRequestDto requestDto, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow();

        if (!user.getId().equals(comment.getUser().getId())) {
            throw new RejectedExecutionException();
        }

        comment.setBody(requestDto.getBody());


        return new CommentResponseDto(comment);
    }

    public void deleteComment(Long id, User user) {
        Comment comment = commentRepository.findById(id).orElseThrow();

        if (!user.getId().equals(comment.getUser().getId())) {
            throw new RejectedExecutionException();
        }
        commentRepository.delete(comment);
    }

}
