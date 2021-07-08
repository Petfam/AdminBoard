package com.petcam.admin.dto.upload;

import com.petcam.admin.entity.board.BoardImage;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminDTO {
    private Long fno;

    private String fname;

    private List<BoardImage> imageList;

}
