package com.petcam.admin.repository.dynamic;


import com.petcam.admin.entity.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardKeywordSearch {

    Page<Board> KeywordSearch (String keyword, Pageable pageable);

}
