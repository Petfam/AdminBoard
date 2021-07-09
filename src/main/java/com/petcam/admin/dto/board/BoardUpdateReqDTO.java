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

    private String type;

    private String title;

    private String content;

    private List<BoardImageDTO> boardDTOImages;

}
