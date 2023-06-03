package com.example.Spring_board.author.controller;


import com.example.Spring_board.author.domain.Author;
import com.example.Spring_board.author.etc.AuthorRequestDto;
import com.example.Spring_board.author.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller
public class AuthorController {

    @Autowired AuthorService authorService;


    // 생성_get
    @GetMapping("/")
    public String authorHome() {
        return "home";
    }

    @GetMapping("authors/new")
    public String authorCreateFrom() {
        return "author/author-register";
    }



    // 생성_post
    @PostMapping("authors/new")
    public String authorCreateFrom(AuthorRequestDto authorRequestDto) throws SQLException {
    // 실무에서는 setter 최대한 배제 -> 이유는 최초 객체 생성시점 뿐만아니라, 여러군대에서 setter를 통해 객체값을 변경가능하게 되어
    // 데이터의 정확성을 보장하기 어렵고, 유비보수가 어렵다. 따라서 생성자를 통하여 매개변수로 입력 받음

        // 방법1. setter 방식 : 최초시점 이외의 다른 클래스에서 객체를값을 변경함으로서, 유지보수의 어려움 발생
        // 방법2. 생성자 방식 (setter배제) -> 문제점은 반드시 매개변수의 순서를 맞춰줘야 한다는 점이고, 매개변수가 많아지면 개발이 어려움
//        Author author1 = new Author(
//
//                authorRequestDto.getName(),
//                authorRequestDto.getEmail(),
//                authorRequestDto.getPassword(),
//                authorRequestDto.getRole()
//
//        );
//
        // 방법3. builder패턴 : 매개변수의 순서와 상관없이 객체 생성가능

        Author author1 = Author.builder()
                        .password(authorRequestDto.getPassword())  // 매개변수의 이름을 지정
                        .name(authorRequestDto.getName())
                        .email(authorRequestDto.getEmail())
                        .role(authorRequestDto.getRole())
                        .build();

        authorService.create(author1);

        return "redirect:/authors";   }

    // 전체 조회
    @GetMapping("authors")
    public String memberFindAll(Model model) throws SQLException {   //타서버 조회시 강제시킴

        List<Author> authors = authorService.findAll();
        model.addAttribute("authorList",authors);
        return "author/author-list";

    }

    //단일 조회
    @GetMapping("author")
    public String authorFindid(@RequestParam(value = "id")Long id,Model model) throws Exception {

        Author author = authorService.findid(id);
        model.addAttribute("author",author);

        return "author/author-detail";

    }


    @PostMapping("author/update")
    public String memberUpdate(AuthorRequestDto authorRequestDto)  throws Exception {
        authorService.update(authorRequestDto);
        return "redirect:/authors";

    }



    // deleteMapping을 사용할 수도 있지만, deleteMapping은 form태그에서는 지원하지 않는다.
    // form태그에서 deleteMapping을 지원하지 않는다는 얘기는 aciton = "delete"를 줄 수 없다는 뜻
    // 그래서, react나 vue.js와 같은 프론트엔드의 특정한 기술을 통해서 delete 요청을 일반적으로 하므로,
    // rest api 방식의 개발(json)에서는 deletemapping이 가능하다.
    @GetMapping("author/delete")
    public String authorDelete(@RequestParam(value="id") String myid) throws SQLException {
        authorService.delete(Long.parseLong(myid));
        return "redirect:/authors";

    }






}
