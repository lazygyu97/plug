package com.sparta.plug.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends TimeStamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String body;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")//sql 강의 join
    private User user;

    public Comment(String body) {
        this.body = body;
    }

//    public void setBody(String body) {
//        this.body = body;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public void setPost(Post post) {
//        this.post = post;
//    }
}
