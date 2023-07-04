package com.sparta.plug.entity;

import com.sparta.plug.dto.PostRequestDto;
import com.sparta.plug.security.UserDetailsImpl;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "posts")
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false, unique = true)
    private String info;

    @Column(nullable = false)
    private String imageUrl;

    //작성한 유저의 정보를 담아 유저 테이블의 외래키로 사용할 칼럼을 생각해야한다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //musicList 정보가 담긴 칼럼
    @OneToMany
    @JoinColumn(name = "post_id")
    private List<MusicList> musicLists = new ArrayList<>();

    public Post(PostRequestDto requestDto, UserDetailsImpl userDetails) {
        this.title = requestDto.getTitle();
        this.info = requestDto.getInfo();
        this.imageUrl = requestDto.getImageUrl();
        // ??
        this.user = userDetails.getUser();
    }
}
