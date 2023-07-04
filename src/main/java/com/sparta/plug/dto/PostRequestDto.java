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

}
