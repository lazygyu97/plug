package com.sparta.plug.entity;

import com.sparta.plug.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "posts")
public class Post extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //작성한 유저의 정보를 담아 유저 테이블의 외래키로 사용할 칼럼을 생각해야한다.
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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
        this.imageUrl=requestDto.getImageUrl();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
