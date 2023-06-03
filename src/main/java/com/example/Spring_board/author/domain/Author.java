package com.example.Spring_board.author.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor  // 기본생성자를 만들어주는 어노테이션
public class Author {

    @Setter
    @Id  // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // GeneratedValue -> auto_Incerement
    private Long id;

    @Setter
    @Column(length = 50)
    private String name;

    @Setter
    @Column(length = 50, unique = true)
    private String email;

    @Setter
    @Column(length = 20)
    private String password;

    @Column(length = 10)
    private String role;

    @Column //mysql에서는 datetime형식으로 컬럼 생성
    private LocalDateTime createDate;

//    생성자 방식과 builder패턴
    @Builder
    public Author(String name, String email, String password, String role){
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.createDate=LocalDateTime.now();

    }

}
