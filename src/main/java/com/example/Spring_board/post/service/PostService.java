package com.example.Spring_board.post.service;

import com.example.Spring_board.author.domain.Author;
import com.example.Spring_board.author.etc.AuthorRequestDto;
import com.example.Spring_board.post.domain.Post;
import com.example.Spring_board.post.etc.PostRequestDto;
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



    public void update(PostRequestDto postRequestDto) throws Exception {
        Post post = postRepository.findById(Long.parseLong(postRequestDto.getId())).orElseThrow(EntityNotFoundException::new);
        if (post == null) {
            throw new Exception();
        } else {

            post.setId(Long.parseLong(postRequestDto.getId()));
            post.setTitle(postRequestDto.getTitle());
            post.setContents(postRequestDto.getContents());

        }
    }

    // 삭제
    public void delete(Long id){
        // 먼저 db에서 조회 후에 author객체를 가져온다.
        // 해당 author객체로 jpa가 delete할 수 있도록 전달해준다.
        postRepository.delete(this.findid(id));

    }






}
