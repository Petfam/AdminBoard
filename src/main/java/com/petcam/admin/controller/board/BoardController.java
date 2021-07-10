package com.petcam.admin.controller.board;

import com.petcam.admin.common.dto.ListResponseDTO;
import com.petcam.admin.dto.board.*;
import com.petcam.admin.repository.board.BoardRepositorys;
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
    private final BoardRepositorys boardRepositorys;

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

    @GetMapping("/select/{bno}")
    public ResponseEntity<BoardResponseDTO> boardSelect(@PathVariable Long bno) {

        log.info("============= board Select");
        boardRepositorys.increaseHit(bno);
        return ResponseEntity.ok(boardService.boardSelect(bno));
    }

    @PutMapping("/update")
    public ResponseEntity<Long> boardUpdate(@RequestBody BoardUpdateReqDTO updateReqDTO) {
        log.info("======= board update");

        return ResponseEntity.ok(boardService.boardUpdate(updateReqDTO));
    }

    @DeleteMapping("/delete/{bno}")
    public ResponseEntity<Long> boardDelete(@PathVariable Long bno) {
        log.info("====== board delete");

        return ResponseEntity.ok(boardService.boardDelete(bno));
    }

}
