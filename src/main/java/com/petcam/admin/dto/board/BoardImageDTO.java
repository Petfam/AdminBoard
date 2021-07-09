package com.petcam.admin.dto.board;

import com.petcam.admin.entity.board.BoardImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class BoardImageDTO {

    private String uuid;

    private String filename;

    private boolean main;
}


