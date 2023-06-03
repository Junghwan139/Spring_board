package com.example.Spring_board.post.controller;

import com.example.Spring_board.author.controller.AuthorController;
import com.example.Spring_board.author.domain.Author;
import com.example.Spring_board.author.repository.AuthorRepository;
import com.example.Spring_board.author.service.AuthorService;
import com.example.Spring_board.post.domain.Post;
import com.example.Spring_board.post.etc.PostRequestDto;
import com.example.Spring_board.post.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;

@Controller
public class PostController {


    @Autowired PostService postService;
    @Autowired AuthorService authorService;

    // 전체 조회
    @GetMapping("posts")
    public String memberFindAll(Model model) throws SQLException {   //타서버 조회시 강제시킴

        List<Post> posts = postService.findAll();
        model.addAttribute("postList",posts);
        return "post/post-list";

    }


    @GetMapping("post/new")
    public String postNew(){
        return "post/post-register";
    }


    @PostMapping("post/new")
    public String postwrite(PostRequestDto post) throws SQLException {
      Author author1 = authorService.findByEmail(post.getEmail());

        Post post1 = Post.builder()
                .title(post.getTitle())  // 매개변수의 이름을 지정
                .contents(post.getContents())
//                post에는 author변수가 있으므로, post생성시 author 객체를 넘겨주면,
//                내부적으로 author 객체에서 author_id를 꺼내어 DB에 넣게 된다.
                .author(author1)
                .build();

        postService.create(post1);

        return "redirect:/posts";
    }



    @GetMapping("post/detail")
    public String postdetail(@RequestParam(value = "id")Long myid,Model model) throws SQLException {

        Post post1 = postService.findid(myid);
        model.addAttribute("post",post1);
        return "post/post-detail";
    }








}
