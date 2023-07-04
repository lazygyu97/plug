package com.sparta.plug.entity;

import com.sparta.plug.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //작성한 유저의 정보를 담아 유저 테이블의 외래키로 사용할 칼럼을 생각해야한다.
    @Column(nullable = false, unique = true)
    private String user;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false, unique = true)
    private String info;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false, unique = true)
    private Long like;


    public Post(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.info = requestDto.getInfo();
        this.imageUrl = requestDto.getImageUrl();
    }
}
