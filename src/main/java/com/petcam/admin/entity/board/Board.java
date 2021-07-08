package com.petcam.admin.entity.board;

import com.petcam.admin.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "admin_board")
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

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BoardImage> boardImages = new HashSet<>();

    public void changeTitle(String title) {
        this.title = title;
    }

    public void addImage(BoardImage image) {
        boardImages.add(image);
    }

    public void removeImage(Long ino) {
        boardImages = boardImages.stream()
                .filter(bi -> bi.getPpno().equals(ino) == false)
                //새로 들어온 이미지 넘버와 안에있는 번호를 비교해서 틀리면 toset 새로 집어넣는다..?
                .collect(Collectors.toSet());
    }


}
