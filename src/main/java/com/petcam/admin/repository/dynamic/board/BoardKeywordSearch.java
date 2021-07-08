package com.petcam.admin.repository.dynamic.board;


import com.petcam.admin.entity.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardKeywordSearch {

    //검색 테스트
    Page<Board> KeywordSearch (String keyword, Pageable pageable);

    //제목, 내용, 작성자, 게시판 타입을 통해 페이징 처리 된 게시물 목록 및 댓글 갯수 호출
    Page<Object[]> getBoardTotalList(String type, String keyword, String category, Pageable pageable);

}
