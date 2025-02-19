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
public class MonamiRubberDTO {
    private int rubberProductionId;

    @NotEmpty
    private LocalDateTime rubberDate;

    @NotEmpty
    private int rubberDefective;

    @NotEmpty
    private int rubberDailyAmount;

    @NotEmpty
    private int rubberDailyProduction;
}
