package com.monmi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<T> {

    private List<T> dtoList;

    @Builder
    public PageResponseDTO(List<T> dtoList) {
        this.dtoList = dtoList;
    }
}

