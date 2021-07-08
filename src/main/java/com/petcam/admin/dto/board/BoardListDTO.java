package com.petcam.admin.dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardListDTO {

    private BoardDTO boardDTO;
    private long replyCount;

}
