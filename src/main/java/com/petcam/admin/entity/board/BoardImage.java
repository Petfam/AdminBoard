package com.petcam.admin.entity.board;

import lombok.*;

import javax.persistence.*;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class BoardImage {

    //조건문이 많이 생길 것 같으면 no를 앞에 생성해서 인덱스로 잡아주는 방법
    @Id
    private String uuid;

    private String filename;

    private boolean main;
}


