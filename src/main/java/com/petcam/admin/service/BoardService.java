package com.petcam.admin.service;

import com.petcam.admin.common.dto.ListResponseDTO;
import com.petcam.admin.dto.board.BoardDTO;
import com.petcam.admin.dto.board.BoardInsertRequestDTO;
import com.petcam.admin.dto.board.BoardListDTO;
import com.petcam.admin.dto.board.BoardListRequestDTO;
import com.petcam.admin.entity.board.Board;
import com.petcam.admin.entity.board.BoardImage;

public interface BoardService {

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

    default Board dtoToEntity(BoardDTO dto) {
        Board board =  Board.builder()
                .bno(dto.getBno())
                .type(dto.getType())
                .category(dto.getType())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .hit(dto.getHit())
                .build();

        return board;
    }

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
