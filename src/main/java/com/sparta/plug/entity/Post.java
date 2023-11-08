package com.sparta.plug.entity;

import com.sparta.plug.dto.PostRequestDto;
import com.sparta.plug.security.UserDetailsImpl;
import com.wrapper.spotify.model_objects.specification.Playlist;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "posts")
public class Post extends TimeStamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)//변경지연 --> 트랜잭션~
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //포스트에 포함된 댓글이 같이 삭제된다.
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String info;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String author;


    //playList 정보가 담길 예정 - 선언은 이곳에  했지만 DB테이블에는 play_list에 컬럼이 생김
    @OneToMany
    @JoinColumn(name = "post_id")
    private List<PlayList> playLists = new LinkedList<>();//


    public Post(PostRequestDto requestDto,User user){
        this.title=requestDto.getTitle();
        this.info= requestDto.getInfo();
        this.imageUrl=requestDto.getImageUrl();
        this.author=user.getNickName();
        this.user=user;
        for(PlayList playList : requestDto.getPlayLists()) {
            this.playLists.add(playList);
        }
    }


    public void update(PostRequestDto postRequestDto) {
        this.title=postRequestDto.getTitle();
        this.info= postRequestDto.getInfo();
        this.imageUrl=postRequestDto.getImageUrl();
        for(PlayList playList : postRequestDto.getPlayLists()) {
            this.playLists.add(playList);
        }
    }
}
