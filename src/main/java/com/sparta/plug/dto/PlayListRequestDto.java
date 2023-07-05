package com.sparta.plug.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayListRequestDto {

    private String name;//곡이름
    private String artist;//가수
    private String imageUrl;//앨범아트
    private String previewUrl;//미리듣기

}
