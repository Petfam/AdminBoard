package com.petcam.admin.repository.dynamic.board;

import com.petcam.admin.common.entity.QBaseEntity;
import com.petcam.admin.entity.board.Board;
import com.petcam.admin.entity.board.QBoard;
import com.petcam.admin.entity.reply.QReply;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class BoardKeywordSearchImpl extends QuerydslRepositorySupport implements BoardKeywordSearch {

    public BoardKeywordSearchImpl() {
        super(Board.class);
    }

    //제목, 내용, 작성자, 게시판 타입을 통해 페이징 처리 된 게시물 목록 및 댓글 갯수 호출
    @Override
    public Page<Object[]> getBoardTotalList(String type, String keyword, String category, Pageable pageable) {

        QBoard board = QBoard.board;
        QReply reply = QReply.reply;

        JPQLQuery<Board> query = from(board);
        query.leftJoin(reply).on(reply.board.eq(board));

        JPQLQuery<Tuple> tuple = query.select(board, reply.countDistinct());

        if(keyword != null && type != null) {
            BooleanBuilder condition = new BooleanBuilder();

            switch (type) {
                case "N":
                    condition.or(board.type.eq("N"));
                    break;
            }

                switch (category) {
                    case "T":
                        condition.or(board.title.contains(keyword));
                        break;
                    case "C":
                        condition.or(board.content.contains(keyword));
                        break;
                    case "W":
                        condition.or(board.writer.contains(keyword));
                        break;
                }//switch end

            tuple.where(condition);

        }

        tuple.where(board.bno.gt(0L));
        tuple.groupBy(board);
        tuple.orderBy(board.bno.desc());

        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> tupleList = tuple.fetch();

        List<Object[]> arrList =
                tupleList.stream().map(tuple1 ->
                    tuple1.toArray()).collect(Collectors.toList());

        long totalCount = tuple.fetchCount();

        return new PageImpl<>(arrList, pageable,totalCount);
    }

    @Override
    public Page<Board> KeywordSearch(String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        JPQLQuery<Board> query = from(board);

        if(keyword != null && keyword.trim().length() != 0) {
            query.where(board.content.contains(keyword));
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
