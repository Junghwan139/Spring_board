package com.example.Spring_board.author.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Author {

    @Id  // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // GeneratedValue -> auto_Incerement
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 50, unique = true)
    private String email;

    @Column(length = 20)
    private String password;

    @Column(length = 10)
    private String role;

    @Column //mysql에서는 datetime형식으로 컬럼 생성
    private LocalDateTime createDate;





}
