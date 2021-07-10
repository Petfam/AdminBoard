package com.petcam.admin.repository.board;

import com.petcam.admin.entity.board.Board;
import com.petcam.admin.repository.dynamic.board.BoardKeywordSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BoardRepositorys extends JpaRepository<Board, Long>, BoardKeywordSearch {

    @Transactional
    @Modifying
    @Query("UPDATE Board b SET b.hit = b.hit + 1 WHERE b.bno = :bno")
    void increaseHit(Long bno);

}
