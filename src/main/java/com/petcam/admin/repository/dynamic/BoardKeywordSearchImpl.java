package com.petcam.admin.repository.dynamic;

import com.petcam.admin.entity.board.Board;
import com.petcam.admin.entity.board.QBoard;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class BoardKeywordSearchImpl extends QuerydslRepositorySupport implements BoardKeywordSearch {

    public BoardKeywordSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> KeywordSearch(String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);
        //select * from board;
        if(keyword != null && keyword.trim().length() != 0) {
            query.where(board.content.contains(keyword)); //where board.content = keyword
        }

        query.where(board.bno.gt(0));
        query.orderBy(board.bno.desc());
        query.offset(pageable.getOffset());
        query.limit(pageable.getPageSize());

        List<Board> result = query.fetch();
        Long countResult = query.fetchCount();

        return new PageImpl<>(result, pageable, countResult);
    }

}
