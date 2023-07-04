package com.sparta.plug.dto;

import com.sparta.plug.entity.Post;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;



@Getter
@Setter
public class PostResponseDto {
    private Long id;
    private String title;
    private String info;
    private String imageUrl;
    private String username;
    private Long like;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.info = post.getInfo();
        this.imageUrl = post.getImageUrl();
        this.like = post.getLike();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();

    }

}

