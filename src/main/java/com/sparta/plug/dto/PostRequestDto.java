package com.sparta.plug.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Getter
@Setter
public class PostRequestDto {



    private String title;

    private String info;

    private String imageUrl;

    private String selectedTracks;
    private List<Track> trackList;

    @Getter
    public static class Track {
        private String name;
        private String artist;

    }

}


