package com.example.Spring_board.author.etc;

import lombok.Getter;
import lombok.Setter;

// input 태그에 name = ""로 넘겨주는 이름을 그대로 class에 사용해야 한다.
// user와 주고받는 data Form 클래스를 일반적으로 DTO(Data Transfer Object)라고 부른다.

@Getter
@Setter // 내부적으로 Setter를 사용해서 html의 input값을 꺼내어 담으므로 setter가 필요
public class AuthorRequestDto {
    private String id;
    private String name;
    private String email;
    private String password;
    private String role;


}
