package com.sparta.plug.dto;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.plug.entity.PlayList;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.List;


@Getter
@Setter
public class PostRequestDto {

    private static ObjectMapper om = new ObjectMapper();

    //valid 어노테이션을 통해 입력받은 값의 검증이 필요하다.
    private String title;
    private String info;
    private String imageUrl;
    private String selectedTracks;

    private List<PlayList> playLists;

    public void stringToList() {
        //stirng -> List: 실행 성공 확인!
        try {
            this.playLists = om.readValue(this.selectedTracks, new TypeReference<List<PlayList>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}


