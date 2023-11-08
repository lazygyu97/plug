package com.sparta.plug.repository;

import com.sparta.plug.entity.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayListRepository extends JpaRepository<PlayList,Long> {
    List<PlayList> findByPostId(Long id);
}
