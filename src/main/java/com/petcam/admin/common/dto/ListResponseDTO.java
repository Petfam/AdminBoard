package com.petcam.admin.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListResponseDTO<DTO> {

    private ListRequestDTO listRequestDTO;

    private List<DTO> dtoList;

    private PagingDTO pagingDTO;

}
