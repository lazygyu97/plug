package com.sparta.plug.repository;

import com.sparta.plug.dto.PostResponseDto;
import com.sparta.plug.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findAllByTitleContains(String keyword);
}
