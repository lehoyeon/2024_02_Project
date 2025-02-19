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
public class MonamiPenbodyDTO {

    private int penbodyProductionId;

    @NotEmpty
    private LocalDateTime penbodyDate;

    @NotEmpty
    private int penbodyDefective;

    @NotEmpty
    private int penbodyDailyAmount;

    @NotEmpty
    private int penbodyDailyProduction;
}
