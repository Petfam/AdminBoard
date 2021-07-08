package com.petcam.admin.dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

    private Long bno;

    private String type;

    private String category;

    private String title;

    private String content;

    private String writer;

    private Long hit;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

}
