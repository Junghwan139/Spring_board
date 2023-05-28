package com.example.Spring_board.study.S1.s1_author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class s1Controller {

    @Autowired s1Service s1sv;

    //home
    @GetMapping("s1_home")
    public String s1_home(){
        return "s1/s1_home";
    }

    //save_Get
    @GetMapping("s1_save")
    public String s1_save_g(){
        return "s1/s1_register";
    }

    //save_Post
    @PostMapping("s1_save")
    public String s1_save_p(s1Author author){
        author.setCreated_at(LocalDateTime.now());
        s1sv.save(author);
        return "redirect:/s1_authors";
    }


    @GetMapping("s1_authors")
    public String s1_authors(Model model){
        model.addAttribute("authorList",s1sv.findAll());
        return "s1/s1_list";
    }








}
