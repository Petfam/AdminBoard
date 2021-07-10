package com.petcam.admin.repository.reply;

import com.petcam.admin.dto.reply.ReplyResDTO;
import com.petcam.admin.entity.reply.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Query("select r.rno, r.rcontent, r.rwriter, r.modDate " +
            " from Reply r " +
            " where r.board.bno = :bno and r.board.bno > 0")
    List<Object[]> getByBoard(Long bno);
    //reply get from bno

}
