package com.monmi.production.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCombDTO {
    private int id;

    @NotEmpty
    private int materialId;

    @NotEmpty
    private int amount;

    @NotEmpty
    private String product;
}
