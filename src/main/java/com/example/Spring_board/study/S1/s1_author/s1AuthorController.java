package com.example.Spring_board.study.S1.s1_author;

import com.example.Spring_board.study.S1.s1_post.s1Post;
import com.example.Spring_board.study.S1.s1_post.s1PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class s1AuthorController {

    @Autowired s1AuthorService s1sv;
    @Autowired s1PostService s1posv;


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

    // find_all
    @GetMapping("s1_authors")
    public String s1_authors(Model model){
        model.addAttribute("authorList",s1sv.findAll());
        return "s1/s1_list";
    }

    // find_one
    @GetMapping("s1_author")
    public String s1_author(@RequestParam(value = "id")Long myid,Model model){
        s1Author author1 = s1sv.findByid(myid);
        model.addAttribute("author",author1);

        int count = 0;
        for(s1Post a : s1posv.s1_findAll()){
            if(a.getAuthor().getId() == author1.getId()){
                count++;
            }
        }
        System.out.println(count);

        return "s1/s1_detail";
    }

    // delete
    @GetMapping("s1_author_delete")
    public String s1_author_delete(@RequestParam(value = "id")Long myid){
        s1sv.delete(myid);
        return "redirect:/s1_authors";
    }

    // update
    @PostMapping("s1_author_update")
    public String s1_author_update(@RequestParam(value = "id")Long myid,s1Author author){
        s1sv.update(author,myid);
        return "redirect:/s1_authors";
    }




}
