package com.petcam.admin.service;

import com.petcam.admin.common.dto.ListResponseDTO;
import com.petcam.admin.dto.board.*;
import com.petcam.admin.dto.reply.ReplyDTO;
import com.petcam.admin.dto.reply.ReplyResDTO;
import com.petcam.admin.entity.board.Board;
import com.petcam.admin.entity.board.BoardImage;
import com.petcam.admin.entity.reply.Reply;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface BoardService {

//    void increaseHit(Long bno);

    Long boardDelete(Long bno);

    Long boardUpdate(BoardUpdateReqDTO updateReqDTO);

    BoardResponseDTO boardSelect(Long bno);

    Long boardInsert(BoardInsertRequestDTO requestDTO);

    ListResponseDTO<BoardListDTO> getList(BoardListRequestDTO requestDTO);

    default BoardListDTO arrToDTO(Object[] arr) {

        Board board = (Board)arr[0];
        long replyCount = (long)arr[1];

        return BoardListDTO.builder()
                .boardDTO(entityToDTO(board))
                .replyCount(replyCount)
                .build();
    }

    default BoardDTO entityToDTO(Board board) {
        return BoardDTO.builder()
                .bno(board.getBno())
                .type(board.getType())
                .category(board.getType())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .hit(board.getHit())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .build();
    }

    default Board dtoToEntityForBoardUpdate(BoardUpdateReqDTO dto) {

        List<BoardImageDTO> imgList = new ArrayList<>(dto.getBoardDTOImages());

        Set<BoardImage> updateResDTO = imgList.stream().map(boardImageDTO ->
                BoardImage.builder()
                        .uuid((boardImageDTO.getUuid()))
                        .filename(boardImageDTO.getFilename())
                        .main(boardImageDTO.isMain())
                        .build())
                .collect(Collectors.toSet());

        return Board.builder()
                .bno(dto.getBno())
                .type(dto.getType())
                .title(dto.getTitle())
                .content(dto.getContent())
                .boardImages(updateResDTO)
                .build();
    }

    default BoardResponseDTO entityToDTOForBoardResponse(Board board, List<ReplyResDTO> replyList) {

        List<BoardImage> imgList = new ArrayList<>(board.getBoardImages());

        List<BoardImageDTO> resDTO = imgList.stream().map(boardImage -> BoardImageDTO.builder()
                    .uuid(boardImage.getUuid())
                    .filename(boardImage.getFilename())
                    .main(boardImage.isMain())
                .build()).collect(Collectors.toList());

        return BoardResponseDTO.builder()
                .bno(board.getBno())
                .type(board.getType())
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getWriter())
                .hit(board.getHit())
                .modDate(board.getModDate())
                .boardDTOImages(resDTO)
                .boardDTOReplys(replyList)
                .build();
    };

    default Board dtoToEntityForInsert(BoardInsertRequestDTO dto) {
        Board board = Board.builder()
                .type(dto.getType())
                .category(dto.getType())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();

        if(dto.getBoardDTOImages() != null) {

            dto.getBoardDTOImages().forEach(boardImageDTO -> {
                        BoardImage image = BoardImage.builder()
                                .uuid(boardImageDTO.getUuid())
                                .filename(boardImageDTO.getFilename())
                                .main(boardImageDTO.isMain())
                                .build();

                        board.addImage(image);
                    }
            );

        }

        return board;
    }

}
