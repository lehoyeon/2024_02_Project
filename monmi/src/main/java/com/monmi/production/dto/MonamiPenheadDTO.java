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
public class MonamiPenheadDTO {
    private int penheadProductionId;

    @NotEmpty
    private LocalDateTime penheadDate;

    @NotEmpty
    private int penheadDefective;

    @NotEmpty
    private int penheadDailyAmount;

    @NotEmpty
    private int penheadDailyProduction;
}
