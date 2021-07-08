package com.petcam.admin.dto;

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
