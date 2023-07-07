package com.sparta.plug.dto;

import com.sparta.plug.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDto extends ApiResponseDto {

    private Long id;
    private String body;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment) {
        super();
        this.id = comment.getId();
        this.body = comment.getBody();
        this.username = comment.getUser().getUsername();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
