package com.monmi.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "financial_summary")
public class FinancialSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "summary_id")
    private Long summaryId;

    @Column(name = "total_revenue")
    private Integer totalRevenue;

    @Column(name = "total_cost")
    private Integer totalCost;

    @Column(name = "net_profit")
    private Integer netProfit;

    @Column(name = "record_date")
    private LocalDate recordDate;

    // Getter & Setter 추가
    public Long getSummaryId() { return summaryId; }
    public Integer getTotalRevenue() { return totalRevenue; }
    public Integer getTotalCost() { return totalCost; }
    public Integer getNetProfit() { return netProfit; }
    public LocalDate getRecordDate() { return recordDate; }

}
