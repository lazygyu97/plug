package com.sparta.plug.service;

import com.sparta.plug.dto.PostListResponseDto;
import com.sparta.plug.dto.PostRequestDto;
import com.sparta.plug.dto.PostResponseDto;
import com.sparta.plug.entity.PlayList;
import com.sparta.plug.entity.Post;
import com.sparta.plug.entity.User;
import com.sparta.plug.entity.UserRoleEnum;
import com.sparta.plug.repository.PlayListRepository;
import com.sparta.plug.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.RejectedExecutionException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PlayListRepository playListRepository;

    //PostDto
    //Post
    public PostListResponseDto getAllPosts() {
        List<PostResponseDto> postList = postRepository.findAll().stream().map(PostResponseDto::new).collect(Collectors.toList());

        return new PostListResponseDto(postList);
    }

    //순환 참조 오류
    public PostResponseDto createPost(PostRequestDto postRequestDto, User user) {

        postRequestDto.stringToList();

        Post post = new Post(postRequestDto, user);
        post.setUser(user);
        //DB에 플리 저장
        savePlayList(post);
        postRepository.save(post);

        return new PostResponseDto(post);
    }

    public void deletePost(Long id, User user) {
        Post post = findPost(id);
        System.out.println(post);
        // 게시글 작성자(post.user) 와 요청자(user) 가 같은지 또는 Admin 인지 체크 (아니면 예외발생)
        if (!post.getUser().getUsername().equals(user.getUsername())) {
            throw new RejectedExecutionException();
        }

        postRepository.delete(post);
    }

    public PostResponseDto getPost(Long id) {
        Post post = findPost(id);
        return new PostResponseDto(post);
    }
    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto, User user) {
        Post post = findPost(id);
        System.out.println(post.getUser().getId());
        System.out.println(user.getId());
        // 게시글 작성자(post.user) 와 요청자(user) 가 같은지 또는 Admin 인지 체크 (아니면 예외발생)
        if (!post.getUser().getId().equals(user.getId())) {
            throw new RejectedExecutionException();
        }
        post.setTitle(requestDto.getTitle());
        post.setInfo(requestDto.getInfo());
        return new PostResponseDto(post);
    }

    public Post findPost(long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("선택한 게시글은 존재하지 않습니다."));
    }

    private void savePlayList (Post post) {
        for(PlayList playList:post.getPlayLists()){
            playListRepository.save(playList);
        }
    }

    public List<PostResponseDto> searchPost(String keyword) {
        if(!postRepository.findAllByTitleContains(keyword).isEmpty()){
            return postRepository.findAllByTitleContains(keyword).stream().map(PostResponseDto::new).toList();
        }else {
           return null;

        }
    }
}
