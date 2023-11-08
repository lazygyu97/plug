package com.sparta.plug.service;

import com.sparta.plug.entity.PlayList;
import com.sparta.plug.repository.PlayListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PlayListService {

    private final PlayListRepository playListRepository;

    public List<PlayList> getPlayList(Long id) {
        return playListRepository.findByPostId(id);
    }
}
