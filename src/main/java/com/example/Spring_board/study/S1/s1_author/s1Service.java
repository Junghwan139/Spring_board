package com.example.Spring_board.study.S1.s1_author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class s1Service {

    @Autowired s1AuthorRepository s1auRepo;

    // 저장
    public void save(s1Author author){
        s1auRepo.save(author);
    }

    // 전체 조회
    public List<s1Author> findAll(){
        return s1auRepo.findAll();
    }

    // 한건 조회
    public s1Author findByEmail(String email){
        return s1auRepo.findByEmail(email);
    }

    // 업데이트
    public void update(s1Author author, String email){

        s1Author author_1 = this.findByEmail(email);
        author_1.setName(author.getName());
        author_1.setPassword(author.getPassword());
        s1auRepo.save(author_1);
    }

    // 삭제
    public void delete(String email){
        s1auRepo.delete(this.findByEmail(email));
    }



}
