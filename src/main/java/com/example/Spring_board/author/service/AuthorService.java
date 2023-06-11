package com.example.Spring_board.author.service;

import com.example.Spring_board.author.domain.Author;
import com.example.Spring_board.author.etc.AuthorRequestDto;
import com.example.Spring_board.author.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class AuthorService implements UserDetailsService {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired   // 의존성 주입(dependency injection - DI)
    private PasswordEncoder passwordEncoder;


    //생성
    public void create(Author author) throws SQLException {
        author.setPassword(passwordEncoder.encode(author.getPassword()));
        authorRepository.save(author);
    }

    // 전체조회
    public List<Author> findAll() throws SQLException {
        List<Author> members = authorRepository.findAll();
        return members;
    }

    // 한건 조회
    public Author findid(Long id) throws EntityNotFoundException {
        Author member = authorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return member;
    }

//    public Author findEmail(Long id) throws EntityNotFoundException {
//        Author member = authorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
//        return member;
//    }

    public Author findByEmail(String email){
        Author author = authorRepository.findByEmail(email);
        return author;
    }



    // 수정
    public void update(AuthorRequestDto authorRequestDto) throws Exception {
        Author author1 = authorRepository.findById(Long.parseLong(authorRequestDto.getId())).orElseThrow(Exception::new);
        if (author1 == null) {
            throw new Exception();
        } else {

            author1.setId(Long.parseLong(authorRequestDto.getId()));
            author1.setName(authorRequestDto.getName());
            author1.setEmail(authorRequestDto.getEmail());
            author1.setPassword(authorRequestDto.getPassword());
            authorRepository.save(author1);

        }
    }

    // 삭제
    public void delete(Long id){
        // 먼저 db에서 조회 후에 author객체를 가져온다.
        // 해당 author객체로 jpa가 delete할 수 있도록 전달해준다.
        authorRepository.delete(this.findid(id));

    }

//    doLogin이라는 spring 내장 메서드가 실행이 될 때,
//    UserDetailsService를 구현한 클래스의 loadByUsername이라는 메서드를 찾는 걸로 약속
//    왜 authorservice에서 하냐면 findmyemail해서 찾아야 하는 기능이 필요한데 별도로 만들면 또 authorwired해야 하니까

    @Override
    // String username은 사용자가 화면에 입력한 eamil주소를 스프링이 받아서 loadUserByUsername에 넣어준다
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // doLogin 내장 기능이 정상 실행되려면, DB에서 조회한 id/pw를 return해줘야 한다.

        Author author = authorRepository.findByEmail(username);

        if(author==null){

        }

        // DB에서 조회한 email, password, 권한을 return, 권한이 없다면 emptyList로 return
        return new User(author.getEmail(),author.getPassword(), Collections.emptyList());
    }


}
