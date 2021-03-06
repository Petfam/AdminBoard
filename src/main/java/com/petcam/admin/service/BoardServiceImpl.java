package com.petcam.admin.service;

import com.petcam.admin.common.dto.ListResponseDTO;
import com.petcam.admin.common.dto.PagingDTO;
import com.petcam.admin.dto.board.BoardListDTO;
import com.petcam.admin.dto.board.BoardListRequestDTO;
import com.petcam.admin.repository.board.BoardRepositorys;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepositorys boardRepositorys;

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
