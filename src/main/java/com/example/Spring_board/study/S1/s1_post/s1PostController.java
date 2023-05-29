package com.example.Spring_board.study.S1.s1_post;

import com.example.Spring_board.study.S1.s1_author.s1AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class s1PostController {

    @Autowired s1PostService s1posv;

    // find_all
    @GetMapping("s1_posts")
    public String s1_postsList(Model model){
        model.addAttribute("posts",s1posv.s1_findAll());
        return "s1/s1_post_list";
    }


    //find_one
    @GetMapping("s1_post")
    public String s1_post_one(@RequestParam(value="id")Long myid,Model model){
        model.addAttribute("post",s1posv.s1_findOne(myid));
        return "s1/s1_post_detail";
    }

    // write_get
    @GetMapping("s1_post_write")
    public String s1_post_write_g(){
        return "s1/s1_post_register";
    }

    // write_post
    @PostMapping("s1_post_write")
    public String s1_post_write_p(s1Post post){
        post.setCreateDate(LocalDateTime.now());
        s1posv.s1_save(post);
        return "redirect:/s1_posts";
    }

    // delete
    @GetMapping("s1_post_delete")
    public String s1_post_del(@RequestParam (value = "id")Long myid){
        s1posv.s1_delete(myid);
        return "redirect:/s1_posts";
    }




}
