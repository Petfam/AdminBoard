package com.petcam.admin.board;

import com.petcam.admin.entity.board.Board;
import com.petcam.admin.repository.board.BoardRepositorys;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
@ActiveProfiles("dev")
public class BoardRepositorysTests {

    @Autowired
    BoardRepositorys boardRepositorys;

    //제목, 내용, 작성자, 게시판 타입을 통해 페이징 처리 된 게시물 목록 및 댓글 갯수 호출
    @Test
    public void testGetBoardTotalList() {

        String type = "N";
        String keyword = "";
        String category = "C";

        Pageable pageable = PageRequest.of(0, 15);

        Page<Object[]> result = boardRepositorys.getBoardTotalList(type, keyword, category, pageable);

        log.info("result: "+result);
        log.info("result count: "+result.getTotalElements());

        result.getContent().forEach(objects -> {
            log.info("result inside: "+objects[0]);
            log.info("result reply: "+objects[1]);
        });
    }

    @Test
    public void testInsert() {

        IntStream.rangeClosed(1, 5000).forEach(i -> {
            long randomHit = (int)(Math.random() * 1000 + 1);
            Board board = Board.builder()
                    .type("N")
                    .category("공지사항"+i)
                    .title("제목"+i)
                    .content("내용"+i)
                    .writer("관리자")
                    .hit(randomHit)
                    .build();

            boardRepositorys.save(board);
        });
    }

    @Test
    public void testSelect() {

        Optional<Board> result = boardRepositorys.findById(2L);
        log.info(result);

    }

    @Test
    public void testDelete() {
        boardRepositorys.deleteById(100L);
    }

    @Test
    public void testUpdate() {

        Optional<Board> result = boardRepositorys.findById(2L);
        result.ifPresent(board -> {
            board.changeTitle("업데이트테스트");
            boardRepositorys.save(board);
        });
    }

    @Test
    public void testPaging() {
        Pageable pageable = PageRequest.of(2, 15);
        Page<Board> result = boardRepositorys.findAll(pageable);

        result.getContent().forEach(board -> {
           log.info(board);
        });

    }

    @Test
    public void testKeywordSearch() {
        String keyword = "바보";

        Pageable pageable = PageRequest.of(0, 10);
        Page<Board> result = boardRepositorys.KeywordSearch(keyword, pageable);
        result.getContent().forEach(board -> {
            log.info(board);
        });

    }

}
