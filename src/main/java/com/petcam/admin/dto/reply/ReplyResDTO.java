package com.petcam.admin.dto.reply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyResDTO {

    private Long rno;

    private String rcontent;

    private String rwriter;

    private LocalDateTime modDate;

}
