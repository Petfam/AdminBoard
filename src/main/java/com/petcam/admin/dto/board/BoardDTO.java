package com.petcam.admin.dto.board;

import com.petcam.admin.entity.board.BoardImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

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

    private Set<BoardImage> boardImages;

    private LocalDateTime regDate;

    private LocalDateTime modDate;

}
