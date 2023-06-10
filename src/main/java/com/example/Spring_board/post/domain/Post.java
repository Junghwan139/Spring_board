package com.example.Spring_board.post.domain;

import com.example.Spring_board.author.domain.Author;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Setter
@NoArgsConstructor
public class Post {


    @Id  // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // GeneratedValue -> auto_Incerement
    private Long id;

    @Column(length = 50)
    private String title;

    @Column
    private String contents;

//    author 테이블의 id를 fk로 잡는다.
//    author_id의 type이 author가 됨에 유의
//    ahthour_id는 author 테이블의 id에 fk가 걸려있음을 Author 객체타입을 지정함으로서 맵핑
//    그리고 Author author 여기서 author라는 변수명은 테이블의 컬럼명이 아니라,
//    추후에 post테이블에서 author를 조회할 때 변수명으로 사용되고, 그 관계성을 ManyToOne으로 표현
//    Author 테이블 입장에서는 1:N의 관계이고, Post테이블의 입장에서는 한 사람이 어러개의 글을 쓸 수 있으므로 N:1이다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name="author_id")  // author_id는 DB에 들어가는 이름.다른 애들은 변수명=DB 이름인데 다름,,
    private Author author;  // Author객체를 타입으로 지정 하였음. author의 id를 가져오라는 정보를 주지 않았는데,, 걸림

    @Column(length = 1)
    private String appointment;

    @Column
    private LocalDateTime appointment_time;


    @Column
    private LocalDateTime createDate;

    @Builder
    public Post(String title, String contents, Author author){
        this.title = title;
        this.contents = contents;
        this.author=author;
        this.createDate=LocalDateTime.now();

    }





}
