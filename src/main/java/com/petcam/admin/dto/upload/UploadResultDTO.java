package com.petcam.admin.dto.upload;

import lombok.*;

import java.util.List;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadResultDTO {

    private String uuid;

    private String filename;

    //둘 다 리스트

}
