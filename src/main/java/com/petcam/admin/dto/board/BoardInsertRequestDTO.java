package com.petcam.admin.dto.board;

import com.petcam.admin.entity.board.BoardImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardInsertRequestDTO {

    private String type;

    private String title;

    private String content;

    private String writer;

    private Set<BoardImage> boardImages;

}
