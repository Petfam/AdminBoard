package com.petcam.admin.reply;

import com.petcam.admin.entity.board.Board;
import com.petcam.admin.entity.reply.Reply;
import com.petcam.admin.repository.reply.ReplyRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
@ActiveProfiles("dev")
public class ReplyRepositoryTests {

    @Autowired
    ReplyRepository replyRepository;

    @Test
    public void testInsert() {

        IntStream.rangeClosed(1, 1000).forEach(i -> {
            long bno = (int)(Math.random() * 200 + 1);

            Board board = Board.builder().bno(bno).build();

            Reply reply = Reply.builder()
                    .rcontent("댓글 내용"+i)
                    .rwriter("댓글 작성자"+i)
                    .board(board)
                    .build();

            replyRepository.save(reply);
        });
    }

    @Test
    public void testRead() {

        Optional<Reply> result = replyRepository.findById(2L);

        log.info(result);

        result.ifPresent(reply -> {
            log.info(reply);
        });

    }

    @Test
    public void testPaging() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("rno").descending());

        Page<Reply> result = replyRepository.findAll(pageable);

        log.info(result);

        result.getContent().forEach(reply -> {
            log.info(reply);
            log.info("===============================");
        });

    }

    @Test
    public void testDelete() {
        replyRepository.deleteById(5L);
    }

    @Test
    public void testUpdate() {
        Optional<Reply> result = replyRepository.findById(2L);
        result.ifPresent(reply -> {
            reply.changeReplyContent("댓글 바뀔 내용");
            replyRepository.save(reply);
        });
    }


}
