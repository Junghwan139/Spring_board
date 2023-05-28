package com.example.Spring_board.author.controller;


import com.example.Spring_board.author.domain.Author;
import com.example.Spring_board.author.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.time.LocalDateTime;
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
    public String authorCreateFrom(@RequestParam(value="name") String myname,
                                   @RequestParam(value="email") String myemail,
                                   @RequestParam(value="password") String mypassword,
                                   @RequestParam(value="role") String myrole) throws SQLException {

        Author author = new Author();
        author.setName(myname);
        author.setEmail(myemail);
        author.setPassword(mypassword);
        author.setCreateDate(LocalDateTime.now());
        author.setRole(myrole);

        authorService.create(author);

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
    public String memberUpdate(@RequestParam(value="id") String myid,
                               @RequestParam(value="name") String myname,
                               @RequestParam(value="email") String myemail,
                               @RequestParam(value="password") String mypassword) throws Exception {

        Author author = new Author();
        author.setId(Long.parseLong(myid));
        author.setName(myname);
        author.setEmail(myemail);
        author.setPassword(mypassword);
        authorService.update(author, author.getId());

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
