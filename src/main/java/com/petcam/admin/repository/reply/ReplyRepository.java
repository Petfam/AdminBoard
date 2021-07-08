package com.petcam.admin.repository.reply;

import com.petcam.admin.entity.board.Board;
import com.petcam.admin.entity.reply.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    Page<Reply> getByBoard(Board board, Pageable pageable);
    //어디에있는지 가져온는거


}
