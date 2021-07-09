package com.petcam.admin.controller.board;

import com.petcam.admin.common.dto.ListResponseDTO;
import com.petcam.admin.dto.board.BoardInsertRequestDTO;
import com.petcam.admin.dto.board.BoardListDTO;
import com.petcam.admin.dto.board.BoardListRequestDTO;
import com.petcam.admin.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adminBoard")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public ResponseEntity<ListResponseDTO<BoardListDTO>> list(@RequestBody BoardListRequestDTO requestDTO) {

        log.info("==========================================");

        return ResponseEntity.ok(boardService.getList(requestDTO));
    }

    @PostMapping("/insert")
    public ResponseEntity<Long> boardInsert(@RequestBody BoardInsertRequestDTO requestDTO) {
        log.info("board insert======================");

        log.info(requestDTO);

        log.info(requestDTO.getBoardDTOImages());

        Long bno = boardService.boardInsert(requestDTO);

        return ResponseEntity.ok(bno);
    }


}
