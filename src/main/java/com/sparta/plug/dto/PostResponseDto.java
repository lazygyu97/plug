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
    //이게 맞나
    private User user;

    public PostResponseDto(Post post){
        this.id = post.getId();
        this.title = post.getTitle();
        this.info = post.getInfo();
        this.imageUrl = post.getImageUrl();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        // ??
        this.user = post.getUser();
    }
}
