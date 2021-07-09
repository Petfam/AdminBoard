package com.petcam.admin.dto.board;

import com.petcam.admin.dto.upload.UploadResultDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardInsertRequestDTO {

    private String type;

    private String title;

    private String content;

    private String writer;

    private List<String> uuid;

    private List<String> filename;

}
