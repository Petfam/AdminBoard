package com.petcam.admin.entity.board;

import com.petcam.admin.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "board")
@ToString
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private Long type;

    private String category;

    private String title;

    private String content;

    private String writer;

    private Long hit;


    public void changeTitle(String title) {
        this.title = title;
    }

}
