package com.example.Spring_board.study.S1.s1_author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class s1AuthorService {

    @Autowired s1AuthorRepository s1auRepo;

    // 저장
    public void save(s1AuthorRequestDto author){
        s1Author author1 = s1Author.builder()
                .name(author.getName())
                .email(author.getEmail())
                .role(author.getRole())
                .password(author.getPassword())
                .build();
        s1auRepo.save(author1);
    }

    // 전체 조회
    public List<s1Author> findAll(){
        return s1auRepo.findAll();
    }

    // 한건 조회
    public s1Author findByid(Long id){
        return s1auRepo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    // 업데이트
    public void update(s1Author author, Long id){

        s1Author author_1 = this.findByid(id);
        author_1.setName(author.getName());
        author_1.setPassword(author.getPassword());
        s1auRepo.save(author_1);
    }

    // 삭제
    public void delete(Long id){
        s1auRepo.delete(this.findByid(id));
    }



}
