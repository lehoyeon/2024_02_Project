package com.monmi.production.repository;

import com.monmi.domain.Material;
import com.monmi.production.domain.MaterialHoldings;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MaterialHoldingsRepository extends JpaRepository<MaterialHoldings, Integer> {
}
