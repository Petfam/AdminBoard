package com.petcam.admin.dto.board;

import com.petcam.admin.dto.reply.ReplyDTO;
import com.petcam.admin.dto.reply.ReplyResDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponseDTO {

    private Long bno;

    private String type;

    private String title;

    private String content;

    private String writer;

    private LocalDateTime modDate;

    private Long hit;

    private List<BoardImageDTO> boardDTOImages;

    private List<ReplyResDTO> boardDTOReplys;

}
