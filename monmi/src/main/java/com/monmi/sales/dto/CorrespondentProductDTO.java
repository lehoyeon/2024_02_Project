package com.monmi.sales.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CorrespondentProductDTO {

    private String companyName;

    @NotEmpty
    private String companyTel;

    @NotEmpty
    private String companyAddress;

    private int productSellTotal;
}
