package com.sparta.plug.dto;

import com.sparta.plug.entity.Post;
import com.sparta.plug.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String info;
    private String imageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private User user;

    public PostResponseDto(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.info = post.getInfo();
        this.imageUrl = post.getImageUrl();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        // 유저 정보를 받아오도록 함
        this.user = post.getUser();
    }
}
