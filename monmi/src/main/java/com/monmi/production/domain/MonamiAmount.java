package com.monmi.production.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Getter
@Builder
public class MonamiAmount {
    @Id
    @Column(name = "product_name")
    private String productName;
    @Column(nullable = false)
    private int amount;
}
