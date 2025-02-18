package com.monmi.production.mapper;

import com.monmi.production.domain.MaterialHoldings;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Mapper
public interface ProductionProcessMapper {
    void materialHoldingsRecentModifybyId(MaterialHoldings materialHoldings);
    void materialHoldingsInsert(MaterialHoldings materialHoldings);
    void monamiPartInsert(String part, LocalDateTime partDate, int partDefective, int partDailyAmount, int partDailyProduction);
    void monamiPartModify(String part, LocalDateTime partDate, int partDefective, int partDailyAmount, int partDailyProduction);
    void monamiTotalpenInsert(LocalDateTime totalpenDate, int totalpenDefective, int totalpenAmount, int totalpenDailyProduction);
    void monamiTotalpenModify(LocalDateTime totalpenDate, int totalpenDefective, int totalpenAmount, int totalpenDailyProduction);
    void monamiAmountItemModify(String productName, int amount);
    int getMonamiAmountItemAmount(String productName);
    int getDailyProduction(String part, LocalDate Date);
    int getDailyAmount(String part, LocalDate Date);
    int getTotalpenDailyAmount(LocalDate Date);
    LocalDateTime getRecentPartDate(String part);
    LocalDateTime getRecentTotalpenDate();
    LocalDateTime getMaterialHoldingsHoldingsDate(int materialId);
    int getMaterialHoldingsQuantity(int materialId);
}
