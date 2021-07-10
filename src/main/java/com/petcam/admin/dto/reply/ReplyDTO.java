package com.petcam.admin.dto.reply;

import com.petcam.admin.entity.board.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@Builder@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {

    private Long rno;

    private String rcontent;

    private String rwriter;

    private Long bno;

    private LocalDateTime regDate;

    private  LocalDateTime modDate;

}
