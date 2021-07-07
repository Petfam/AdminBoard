package com.petcam.admin.board;

import com.petcam.admin.entity.board.Board;
import com.petcam.admin.repository.board.BoardRepository;
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
public class BoardRepositoryTests {

    @Autowired
    BoardRepository boardRepository;

    @Test
    public void testInsert() {

        double randomHit = (Math.random() * 1000) + 1;
        Long randomHitLong = (new Double(randomHit)).longValue();

        IntStream.rangeClosed(1,10000).forEach(i -> {
            Board board = Board.builder()
                    .type(1L)
                    .category("공지사항"+i)
                    .title("제목"+i)
                    .content("내용"+i)
                    .writer("관리자")
                    .hit(randomHitLong)
                    .build();

            boardRepository.save(board);
        });
    }

    @Test
    public void testSelect() {

        Optional<Board> result = boardRepository.findById(2L);
        log.info(result);

    }

    @Test
    public void testDelete() {
        boardRepository.deleteById(100L);
    }

    @Test
    public void testUpdate() {

        Optional<Board> result = boardRepository.findById(2L);
        result.ifPresent(board -> {
            board.changeTitle("업데이트테스트");
            boardRepository.save(board);
        });
    }

    @Test
    public void testPaging() {
        Pageable pageable = PageRequest.of(2, 15);
        Page<Board> result = boardRepository.findAll(pageable);

        result.getContent().forEach(board -> {
           log.info(board);
        });

    }

    @Test
    public void testKeywordSearch() {
        String keyword = "바보";

        Pageable pageable = PageRequest.of(0, 10);
        Page<Board> result = boardRepository.KeywordSearch(keyword, pageable);
        result.getContent().forEach(board -> {
            log.info(board);
        });

    }

}
