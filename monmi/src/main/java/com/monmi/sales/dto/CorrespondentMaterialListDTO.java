package com.monmi.sales.dto;

import com.monmi.dto.MaterialDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CorrespondentMaterialListDTO {
    private List<MaterialDTO> dtoList;
}
