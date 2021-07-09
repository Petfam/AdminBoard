package com.petcam.admin.upload;

import com.petcam.admin.entity.board.Board;
import com.petcam.admin.entity.board.BoardImage;
import com.petcam.admin.repository.board.BoardRepositorys;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("dev")
@Log4j2
public class FileRepositoryTests {

    @Autowired
    BoardRepositorys boardRepository;

    // 새로운 타이틀하나를 만들고 파일2개를 추가해라
    @Test
    public void imageInsert() {
        Board board = Board.builder().title("새로운제목").build();

        board.addImage(BoardImage.builder().uuid("파일이름1").build());
        board.addImage(BoardImage.builder().uuid("파일이름2").build());
        // board title 가입인사 가 새로생겼고 그 bno 번호에 맞춰서
        // 중간 페이블에서 bno 번호 와  그에맞는 이미지이름이 2개가 생긴것을 볼수있다
        // 게시판하나에 파일이 여러개일경우..
        // 보드이미지 엔티티를 가져올때 oneToMany 그리고 set으로 가져왔기 때문에 add 로 값을 집어넣어주고있다
        boardRepository.save(board);
    }

    //  read test
    @Test
    @Transactional
    // 1002 번째 번호로 찾아서 제목과 파일만 뽑아내봐라
    public void testRegister() {

        Optional<Board> result = boardRepository.findById(1002L);

        if (result.isPresent()) {
            Board board = result.get();
            log.info(board.getTitle());
            log.info(board.getBoardImages());
        }


        // if result.isPresent() get 후 엔티티에 다시담아야한다
        // 저런방식을 쓰지않고 ifpreesnt 람다로 만 돌리면 title은 확인되지만 이미지가 정상적으로 출력되지않는다

    }
}