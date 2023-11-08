package com.sparta.plug.dto;

import com.sparta.plug.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String info;
    private String imageUrl;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
//    private List<CommentResponseDto> comments;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.info = post.getInfo();
        this.imageUrl = post.getImageUrl();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.author = post.getUser().getNickName();
//        this.comments = post.getComments().stream()
//                .map(CommentResponseDto::new)
//                .sorted(Comparator.comparing(CommentResponseDto::getCreatedAt).reversed()) // 작성날짜 내림차순
//                .toList();
    }

    // title 리스트를 반환하는 메서드 추가
    public static List<String> getTitleList(List<PostResponseDto> posts) {
        return posts.stream()
                .map(PostResponseDto::getTitle)
                .collect(Collectors.toList());
    }

}
