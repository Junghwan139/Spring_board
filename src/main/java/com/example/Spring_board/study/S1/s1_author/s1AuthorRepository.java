package com.example.Spring_board.study.S1.s1_author;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface s1AuthorRepository extends JpaRepository<s1Author, Long> {

    s1Author findByEmail(String email);





}
