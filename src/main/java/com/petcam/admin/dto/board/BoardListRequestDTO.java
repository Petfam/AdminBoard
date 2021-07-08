package com.petcam.admin.dto.board;

import com.petcam.admin.common.dto.ListRequestDTO;
import lombok.Data;

@Data
public class BoardListRequestDTO extends ListRequestDTO {

    private String type;
    private String category;

}
