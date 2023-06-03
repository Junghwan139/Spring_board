package com.example.Spring_board.post.service;

import com.example.Spring_board.post.domain.Post;
import com.example.Spring_board.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLException;
import java.util.List;

@Service
public class PostService {

    @Autowired PostRepository postRepository;

    //생성
    public void create(Post post) throws SQLException {
        postRepository.save(post);
    }

    // 전체조회
    public List<Post> findAll() throws SQLException {
        List<Post> posts = postRepository.findAll();
        return posts;
    }

    // 한건 조회
    public Post findid(Long id) throws EntityNotFoundException {
        Post post = postRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return post;
    }








}
