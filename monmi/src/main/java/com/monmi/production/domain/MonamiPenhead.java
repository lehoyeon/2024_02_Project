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
public class MonamiPenhead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "penhead_production_id")
    private int penheadProductionId;
    @Column(name = "penhead_date")
    private LocalDateTime penheadDate;
    @Column(name = "penhead_defective")
    private int penheadDefective;
    @Column(name = "penhead_daily_amount")
    private int penheadDailyAmount;
    @Column(name = "penhead_daily_production")
    private int penheadDailyProduction;
}
