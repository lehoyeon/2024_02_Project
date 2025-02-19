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
public class MonamiPenbody {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "penbody_production_id")
    private int penbodyProductionId;
    @Column(name = "penbody_date")
    private LocalDateTime penbodyDate;
    @Column(name = "penbody_defective")
    private int penbodyDefective;
    @Column(name = "penbody_daily_amount")
    private int penbodyDailyAmount;
    @Column(name = "penbody_daily_production")
    private int penbodyDailyProduction;
}
