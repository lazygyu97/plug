package com.sparta.plug.repository;

import com.sparta.plug.dto.CommentResponseDto;
import com.sparta.plug.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPostId(Long id);
}
