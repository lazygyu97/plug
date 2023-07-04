package com.sparta.plug.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class PostRequestDto {

    private String title;
    private String info;
    private String imageUrl;

/*
    private List musicList;
    private String musicTitle;
    private String artist;
    private Long like;
*/

/*    private String password;
    private boolean deleteFlag;

    private String keyword;*/

}
