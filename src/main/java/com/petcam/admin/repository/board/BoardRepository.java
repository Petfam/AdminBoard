package com.petcam.admin.repository.board;

import com.petcam.admin.entity.board.Board;
import com.petcam.admin.repository.dynamic.BoardKeywordSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardKeywordSearch {

    @Query("select b from Board b where b.content like concat('%', :keyword, '%') order by b.bno desc")
    Page<Board> getList(String keyword, Pageable pageable);

}
