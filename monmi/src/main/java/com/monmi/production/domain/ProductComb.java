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
public class ProductComb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "materiral_id", nullable = false)
    private int materialId;
    @Column(nullable = false)
    private int amount;
    @Column
    private String product;
}
