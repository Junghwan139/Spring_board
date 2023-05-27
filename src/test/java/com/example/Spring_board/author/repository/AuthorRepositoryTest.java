package com.example.Spring_board.author.repository;

import com.example.Spring_board.author.domain.Author;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

//DataJpaTest를 사용하면 매 테스트코드가 종료되면 자동으로 DB가 원상복구(롤백)
@DataJpaTest
//test용의 configure를 사용하는 AutoConfigureTestDatabase.Replace가 기본 설정
// 그런데 기존의 configure(application.yml)을 사용하려면 None으로 셋팅
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

//    테스트시나리오
//    테스트 데이터를 save시키고, save된 데이터를 다시 조회해서 테스트데이터와 비교하면
//    repository의 save, find기능이 정상인지 검증할 수 있다.

//    테스트코드의 장점은 부족한 기능을 사전에 알아차릴 수 있다.

    @Test
    public void authorSave(){
        Author authorInput = new Author();
        authorInput.setName("hong");
        authorInput.setEmail("hong@naver.com");
        authorInput.setPassword("1234");


        authorRepository.save(authorInput);


//        저장된 데이터를 다시 조회해서, 입력한 테스트 데이터와 동일한지 검증
//        조회를 해야 하는데, id를 모르므로 기본 메서드인 findById를 사용할수가 없으므로,
//        repository에 findByEmail 추가

        Author authorDB = authorRepository.findByEmail("hong@naver.com");

//        input값과 조회한 값이 같은지를 비교해 보는 방법
//        방법1 : print 해 본다.
//        System.out.println("name : " + authorDB.getName());
//        System.out.println("Email : " + authorDB.getEmail());
//        System.out.println("Password : " + authorDB.getPassword());
        
//        방법2 : 실행 시 에러가 나면 빨간불, 정상이면 파란불로 구분할 수 있도록 
//        JUnit의 기능 활용
//        Junit이란 java/spring에서 테스트 용도의 tool로 가장 많이 사용되는 라이브러리

        Assertions.assertEquals(authorInput,authorDB);





    }





}
