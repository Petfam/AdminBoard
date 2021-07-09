package com.petcam.admin.repository.upload;

import com.petcam.admin.entity.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UploadRepository extends JpaRepository<Board, Long> {

    @Query("select b from Board b where b.content like concat('%',:keyword,'%') order by b.bno")
    Page<Board> getList(String keyword, Pageable pageable);

    @Query("select b.bno, b.title ,b.content ,b.writer ,bi.uuid ,bi.fileName " +
            "from Board b left join b.boardImages bi " +
            "where bi.main=true and b.bno > 0 group by b")
    Page<Object[]> getBoardList(Pageable pageable);

}
