package com.petcam.admin.dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardUpdateReqDTO {

    private Long bno;

    private String type;

    private String title;

    private String content;

    private List<BoardImageDTO> boardDTOImages;

}
