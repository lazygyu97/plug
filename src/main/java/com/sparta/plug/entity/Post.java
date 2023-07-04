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
public class Post extends TimeStamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String info;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String author;



    public Post(PostRequestDto requestDto,User user){
        this.title=requestDto.getTitle();
        this.info= requestDto.getInfo();
        this.imageUrl=requestDto.getImageUrl();
        this.author=user.getNickName();
        this.user=user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
