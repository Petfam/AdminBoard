package com.petcam.admin.dto.upload;

import lombok.*;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadResultDTO {

    private String uuid;
    private String fileName;
}
