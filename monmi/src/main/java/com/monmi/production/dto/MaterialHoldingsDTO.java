package com.monmi.production.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterialHoldingsDTO {

    private int id;

    @NotEmpty
    private int materialId;

    @NotEmpty
    private int quantity;

    @NotEmpty
    private LocalDateTime holdingsDate;
}
