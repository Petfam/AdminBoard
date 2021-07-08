package com.petcam.admin.entity.reply;

import com.petcam.admin.common.entity.BaseEntity;
import com.petcam.admin.entity.board.Board;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "admin_reply")
@ToString(exclude = "board")
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String rcontent;

    private String rwriter;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    public void changeReplyContent(String rcontent) {
        this.rcontent = rcontent;
    }


}
