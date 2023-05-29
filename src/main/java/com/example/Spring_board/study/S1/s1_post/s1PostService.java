package com.example.Spring_board.study.S1.s1_post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class s1PostService {

    @Autowired s1PostRepository s1PoRepo;

    // save
    public void s1_save(s1Post post){
        s1PoRepo.save(post);
    }

    // find_all
    public List<s1Post> s1_findAll(){
        return s1PoRepo.findAll();
    }

    // find_one
    public s1Post s1_findOne(Long id){
        return s1PoRepo.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    // delete
    public void s1_delete(Long id){
        s1PoRepo.delete(this.s1_findOne(id));
    }

    // update
    public void s1_update(s1Post newPost, Long id){
        s1Post beforeToChange = this.s1_findOne(id);
        beforeToChange.setId(newPost.getId());
        beforeToChange.setContents(newPost.getContents());
        beforeToChange.setTitle(newPost.getTitle());
        s1PoRepo.save(beforeToChange);
    }


}
