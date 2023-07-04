package com.sparta.plug.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

@Getter
@Setter
public class PostRequestDto {



    private String title;

    private String info;

    private String imageUrl;



}
