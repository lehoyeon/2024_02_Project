package com.monmi.production.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Getter
@Builder
public class MonamiTotalpen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "totalpen_production_id")
    private int totalpenProductionId;
    @Column(name = "totalpen_date")
    private LocalDateTime totalpenDate;
    @Column(name = "totalpen_amount")
    private int totalpenAmount;
    @Column(name = "totalpen_daily_production")
    private int totalpenDailyProduction;
}
