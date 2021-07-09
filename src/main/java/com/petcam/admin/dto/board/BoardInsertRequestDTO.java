package com.petcam.admin.dto.board;

import com.petcam.admin.dto.reply.ReplyDTO;
import com.petcam.admin.dto.upload.UploadResultDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardInsertRequestDTO {

    private String type;

    private String title;

    private String content;

    private String writer;

    private UploadResultDTO files;

}
