package com.petcam.admin.service;

import com.petcam.admin.common.dto.ListResponseDTO;
import com.petcam.admin.common.dto.PagingDTO;
import com.petcam.admin.dto.board.*;
import com.petcam.admin.dto.reply.ReplyResDTO;
import com.petcam.admin.entity.board.Board;
import com.petcam.admin.entity.reply.Reply;
import com.petcam.admin.repository.board.BoardRepositorys;
import com.petcam.admin.repository.reply.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepositorys boardRepositorys;
    private final ReplyRepository replyRepository;

//    @Override
//    public void increaseHit(Long bno) {
//
//        Optional<Board> result = boardRepositorys.findById(bno);
//
//        if(result.isPresent()) {
//            Board board = Board.builder()
//                    .bno(result.get().getBno())
//                    .hit(+1L)
//                    .build();
//            boardRepositorys.save(board);
//        }
//    }

    @Override
    public Long boardDelete(Long bno) {

        boardRepositorys.deleteById(bno);

        return bno;
    }

    @Override
    public Long boardUpdate(BoardUpdateReqDTO updateReqDTO) {

            Board board = dtoToEntityForBoardUpdate(updateReqDTO);
            boardRepositorys.save(board);
            return board.getBno();

    }

    @Override
    public BoardResponseDTO boardSelect(Long bno) {

        log.info("===================== boardSelect Start");

        BoardResponseDTO dto = null;

        Optional<Board> result = boardRepositorys.findById(bno);
        List<Object[]> replyTempoList = replyRepository.getByBoard(bno);

        List<ReplyResDTO> replyList = replyTempoList.stream().map(objects ->
            ReplyResDTO.builder()
                    .rno((Long)objects[0])
                    .rcontent((String)objects[1])
                    .rwriter((String)objects[2])
                    .modDate((LocalDateTime)objects[3])
                    .build()
                ).collect(Collectors.toList());

        if(result.isPresent()) {
            Board board = result.get();
            dto = entityToDTOForBoardResponse(board, replyList);
        }

        return dto;
    }

    @Override
    public Long boardInsert(BoardInsertRequestDTO requestDTO) {

        log.info("======================== boardInsert Start");

        Board board = dtoToEntityForInsert(requestDTO);

        log.info(board);

        boardRepositorys.save(board);

        return board.getBno();

    }

    @Override
    public ListResponseDTO<BoardListDTO> getList(BoardListRequestDTO requestDTO) {

        log.info("========================= requestDTO: "+requestDTO);

        Page<Object[]> result = boardRepositorys.getBoardTotalList(requestDTO.getType(), requestDTO.getKeyword(), requestDTO.getCategory(), requestDTO.getPageable());
        List<BoardListDTO> boardDTOList =
                result.getContent().stream().map(arr -> arrToDTO(arr)).collect(Collectors.toList());

        PagingDTO pagingDTO = new PagingDTO(requestDTO.getPage(), requestDTO.getSize(), (int)result.getTotalElements());

        log.info(result);

        return ListResponseDTO.<BoardListDTO>builder()
                .dtoList(boardDTOList)
                .pagingDTO(pagingDTO)
                .listRequestDTO(null)
                .build();

    }

}
