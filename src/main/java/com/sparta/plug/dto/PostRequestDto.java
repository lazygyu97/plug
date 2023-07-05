package com.sparta.plug.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostRequestDto {

    //valid 어노테이션을 통해 입력받은 값의 검증이 필요하다.
    private String title;
    private String info;
    private String imageUrl;
    private String selectedTracks;
    private List<PlayListRequestDto> trackList;



}


