package com.sparta.plug.dto;

import com.sparta.plug.entity.Post;

public class PostResponseDto {
    private Long id;
    private String title;
    private String info;
    private String imageUrl;
    private Long like;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.info = post.getInfo();
        this.imageUrl = post.getImageUrl();
        this.like = post.getLike();

    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Long getLike() {return like;}
}

