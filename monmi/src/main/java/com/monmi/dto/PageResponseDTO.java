package com.monmi.dto;

import com.monmi.sales.dto.CorrespondentMaterialDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO {

    private List<CorrespondentMaterialDTO> dtoList;

    @Builder
    public PageResponseDTO(List<CorrespondentMaterialDTO> dtoList) {
        this.dtoList = dtoList;
    }
}

