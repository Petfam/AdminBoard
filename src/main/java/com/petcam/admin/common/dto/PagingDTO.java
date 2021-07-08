package com.petcam.admin.common.dto;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PagingDTO {

    private int page;
    private int size;
    private int totalCount;
    private List<Integer> pageList;
    private boolean prev, next;

    public PagingDTO(int page,int size, int totalCount) {
        this.page = page;
        this.size = size;
        this.totalCount = totalCount;

        int totalPage = (int)(Math.ceil(totalCount/(double)size));
        
        //임시 마지막 페이지
        int tempEnd = (int)(Math.ceil(page/10.0)) * 10;

        int start = tempEnd - 9;

        prev = start > 1;

        int end = totalPage > tempEnd ? tempEnd: totalPage;

        next = totalPage > tempEnd;

        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }

}
