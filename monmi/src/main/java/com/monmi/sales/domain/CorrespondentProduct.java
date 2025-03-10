package com.monmi.sales.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Getter
@Builder
public class CorrespondentProduct {
    @Id
    @Column(name = "company_name", length = 300, nullable = false)
    private String companyName;
    @Column(name = "company_tel", length = 200, nullable = false)
    private String companyTel;
    @Column(name = "company_address", length = 200, nullable = false)
    private String companyAddress;
    @Column(name = "product_sell_total", nullable = false)
    private int productSellTotal;
}
