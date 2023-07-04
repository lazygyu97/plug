package com.sparta.plug.service;

import com.sparta.plug.dto.PostRequestDto;
import com.sparta.plug.entity.Post;
import com.sparta.plug.entity.User;
import com.sparta.plug.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.awt.SystemColor.info;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // 게시물 생성
    public Post createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);

        return postRepository.save(post);
    }

    // 게시물 조회 (전체)
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // 게시물 조회 (단일)
    public Post getPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));
    }

    // 게시물 수정
    public Post updatePost(Long id, PostRequestDto requestDto) {
        Post post = getPost(id);
        post.setTitle(requestDto.getTitle());
        post.setInfo(requestDto.getInfo());
        post.setImageUrl(requestDto.getImageUrl());
        return postRepository.save(post);
    }

    // 게시물 삭제
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }



}
