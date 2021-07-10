package com.petcam.admin.board;

import com.petcam.admin.entity.board.Board;
import com.petcam.admin.entity.board.BoardImage;
import com.petcam.admin.repository.board.BoardRepositorys;
import com.petcam.admin.repository.upload.UploadRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
@ActiveProfiles("dev")
public class BoardRepositorysTests {

    @Autowired
    BoardRepositorys boardRepositorys;

    @Autowired
    UploadRepository uploadRepository;

    @Test
    public void testBoardSelect() {

    }

    @Test
    public void testBoardAndPicInsert() {

        BoardImage img = BoardImage.builder()
                .uuid("1")
                .filename("456")
                .main(false)
                .build();

        BoardImage img2 = BoardImage.builder()
                .uuid("2")
                .filename("456")
                .main(false)
                .build();


        Board board = Board.builder()
                .type("N")
                .title("제목1")
                .content("내용1")
                .writer("쓴사람1")
                .build();

        board.addImage(img);
        board.addImage(img2);

        boardRepositorys.save(board);

    }

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
                    .category("카테고리"+i)
                    .title("제목"+i)
                    .content("내용"+i)
                    .writer("관리자")
                    .hit(randomHit)
                    .build();

            boardRepositorys.save(board);
        });
    }

    @Test
    @Transactional
    public void testSelect() {

        Optional<Board> result = boardRepositorys.findById(5024L);
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
