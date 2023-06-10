package com.example.Spring_board.post.etc;


import com.example.Spring_board.author.domain.Author;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostRequestDto {


    private String id;
    private String title;
    private String contents;
    private String email;
    private String appointment;
    private LocalDateTime appointment_time;




}
