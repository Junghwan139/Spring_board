package com.example.Spring_board.post.service;

import com.example.Spring_board.author.domain.Author;
import com.example.Spring_board.author.service.AuthorService;
import com.example.Spring_board.post.domain.Post;
import com.example.Spring_board.post.etc.PostRequestDto;
import com.example.Spring_board.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional  // 사용하면 각 메서드가 하나의 트랜젝션으로 묶임. 중간에 하나가 에러날 수 있음 그럴경우 다같이 롤백 처리됨
public class PostService {

    @Autowired PostRepository postRepository;
    @Autowired AuthorService authorService;

    //생성
    public void create(PostRequestDto post) throws SQLException {


        Author author1 = authorService.findByEmail(post.getEmail());

        String my_appointment = null;
        LocalDateTime my_appointment_time = null;
        if(post.getAppointment() != null && !post.getAppointment_time().isEmpty()){

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime localDateTime = LocalDateTime.parse(post.getAppointment_time(),formatter);
            LocalDateTime currentTime = LocalDateTime.now();

            if(currentTime.isBefore(localDateTime) == true){
                my_appointment_time = localDateTime;
                my_appointment = "Y";
            }

        }

        Post post1 = Post.builder()
                .title(post.getTitle())  // 매개변수의 이름을 지정
                .contents(post.getContents())
//                post에는 author변수가 있으므로, post생성시 author 객체를 넘겨주면,
//                내부적으로 author 객체에서 author_id를 꺼내어 DB에 넣게 된다.
                .author(author1)
                .appointment(my_appointment)
                .appointment_time(my_appointment_time)
                .build();

        postRepository.save(post1);
    }

    // 전체조회
    public List<Post> findAll() throws SQLException {
        List<Post> posts = postRepository.findByAppointment(null);

        return posts;
    }

    // 한건 조회
    public Post findid(Long id) throws EntityNotFoundException {
        Post post = postRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return post;
    }



    public void update(PostRequestDto postRequestDto) throws Exception {
        Post post = postRepository.findById(Long.parseLong(postRequestDto.getId())).orElseThrow(EntityNotFoundException::new);
        if (post == null) {
            throw new Exception();
        } else {

            post.setId(Long.parseLong(postRequestDto.getId()));
            post.setTitle(postRequestDto.getTitle());
            post.setContents(postRequestDto.getContents());

            postRepository.save(post);

        }
    }

    // 삭제
    public void delete(Long id){
        // 먼저 db에서 조회 후에 author객체를 가져온다.
        // 해당 author객체로 jpa가 delete할 수 있도록 전달해준다.
        postRepository.delete(this.findid(id));

    }






}
