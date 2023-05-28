package com.example.Spring_board.author.service;

import com.example.Spring_board.author.domain.Author;
import com.example.Spring_board.author.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.SQLException;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    //생성
    public void create(Author author) throws SQLException {
        authorRepository.save(author);
    }

    // 전체조회
    public List<Author> findAll() throws SQLException {
        List<Author> members = authorRepository.findAll();
        return members;
    }

    // 한건 조회
    public Author findid(Long id) throws EntityNotFoundException {
        Author member = authorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return member;
    }

    // 수정
    public void update(Author author, Long id) throws Exception {
        Author author1 = authorRepository.findById(author.getId()).orElse(null);
        if (author1 == null) {
            throw new Exception();
        } else {
            author1.setId(author.getId());
            author1.setName(author.getName());
            author1.setEmail(author.getEmail());
            author1.setPassword(author.getPassword());
            authorRepository.save(author1);
        }
    }

    // 삭제
    public void delete(Long id){
        // 먼저 db에서 조회 후에 author객체를 가져온다.
        // 해당 author객체로 jpa가 delete할 수 있도록 전달해준다.
        authorRepository.delete(this.findid(id));

    }




}
