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
public class MaterialHoldings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JoinColumn(name = "material_id", nullable = false)
    private int materialId;
    @Column(nullable = false)
    private int quantity;
    @Column(name = "holdings_date", nullable = false)
    private LocalDateTime holdingsDate;
}
