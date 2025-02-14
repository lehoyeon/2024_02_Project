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
public class MonamiTotalpenDTO {
    private int totalpenProductionId;

    @NotEmpty
    private LocalDateTime totalpenDate;

    @NotEmpty
    private int totalpenAmount;

    @NotEmpty
    private int totalpenDailyProduction;
}
