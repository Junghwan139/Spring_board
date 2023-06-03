package com.example.Spring_board.post.etc;


import com.example.Spring_board.author.domain.Author;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDto {


    private String id;
    private String title;
    private String contents;
    private String email;


}
