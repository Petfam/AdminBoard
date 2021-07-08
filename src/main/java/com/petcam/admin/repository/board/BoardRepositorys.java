package com.petcam.admin.repository.board;

import com.petcam.admin.entity.board.Board;
import com.petcam.admin.repository.dynamic.board.BoardKeywordSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepositorys extends JpaRepository<Board, Long>, BoardKeywordSearch {

}
