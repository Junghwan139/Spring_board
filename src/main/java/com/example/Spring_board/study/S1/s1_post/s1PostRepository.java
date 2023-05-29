package com.example.Spring_board.study.S1.s1_post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface s1PostRepository extends JpaRepository<s1Post, Long> {

}
