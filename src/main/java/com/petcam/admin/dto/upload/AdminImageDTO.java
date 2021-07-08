package com.petcam.admin.dto.upload;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminImageDTO {
    private Long fno;

    private String fname;
}
