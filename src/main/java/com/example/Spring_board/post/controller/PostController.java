package com.example.Spring_board.post.controller;

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
        postService.create(post);

    return "redirect:/posts";
    }



    @GetMapping("post/detail")
    public String postdetail(@RequestParam(value = "id")Long myid,Model model) throws SQLException {

        Post post1 = postService.findid(myid);
        model.addAttribute("post",post1);
        return "post/post-detail";
    }



    @PostMapping("post/update")
    public String memberUpdate(PostRequestDto postRequestDto)  throws Exception {

        postService.update(postRequestDto);

        return "redirect:/posts";

    }



    // deleteMapping을 사용할 수도 있지만, deleteMapping은 form태그에서는 지원하지 않는다.
    // form태그에서 deleteMapping을 지원하지 않는다는 얘기는 aciton = "delete"를 줄 수 없다는 뜻
    // 그래서, react나 vue.js와 같은 프론트엔드의 특정한 기술을 통해서 delete 요청을 일반적으로 하므로,
    // rest api 방식의 개발(json)에서는 deletemapping이 가능하다.
    @GetMapping("post/delete")
    public String postDelete(@RequestParam(value="id") String myid) throws SQLException {
        postService.delete(Long.parseLong(myid));
        return "redirect:/posts";

    }




}
