package com.sparta.plug.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class CommentListResponseDto {
    private List<CommentResponseDto> commentsList;
    public CommentListResponseDto(List<CommentResponseDto> commentsList) {
        this.commentsList = commentsList;
    }
}
