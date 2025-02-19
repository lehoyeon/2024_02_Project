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
public class MonamiRubber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rubber_production_id")
    private int rubberProductionId;
    @Column(name = "rubber_date")
    private LocalDateTime rubberDate;
    @Column(name = "rubber_defective")
    private int rubberDefective;
    @Column(name = "rubber_daily_amount")
    private int rubberDailyAmount;
    @Column(name = "rubber_daily_production")
    private int rubberDailyProduction;
}
