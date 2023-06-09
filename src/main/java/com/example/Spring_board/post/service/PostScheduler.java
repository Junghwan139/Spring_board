package com.example.Spring_board.post.service;

import com.example.Spring_board.post.domain.Post;
import com.example.Spring_board.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class PostScheduler {

    @Autowired PostRepository postRepository;

    // 스케쥴러는 초분시일월주 등 시간 옵션을 줘야 몇분에 한번, 몇시간에 한번 등 시간을 주기로 돌아간다.
    // 일반적으로 cron이란 리눅스/스프링 그 외 각종 프레임워크에서 스케쥴러를 의미한다.
    @Scheduled(cron="0 0/1 * * * *")
    public void postSchedule(){
        System.out.printf("스케쥴러 입니다.");

        List<Post> posts = postRepository.findByAppointment("Y");

        LocalDateTime currentTime = LocalDateTime.now();

        for(Post a : posts){
            if(currentTime.isAfter(a.getAppointment_time()) == true){

                Post post1 = a;
                post1.setAppointment(null);
                postRepository.save(post1);

            }

        }





    }


}
