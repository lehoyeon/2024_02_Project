package com.monmi.production.repository;

import com.monmi.production.domain.MonamiAmount;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MonamiAmountRepository extends JpaRepository<MonamiAmount, String> {

    @Query("select b.amount from MonamiAmount b where b.productName = :productName")
    Integer findAmountByProductName(@Param("productName") String productName);
}
