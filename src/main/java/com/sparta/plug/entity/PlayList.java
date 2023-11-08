package com.sparta.plug.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@Table(name = "playList")
public class PlayList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(nullable = false)
    @JsonProperty("name")
    private String name;

    @JsonProperty("artist")
    @Column(nullable = false)
    private String artist;

    @JsonProperty("imageUrl")
    @Column(nullable = false)
    private String imageUrl;

    @JsonProperty("previewUrl")
    @Column
    private String previewUrl;
}