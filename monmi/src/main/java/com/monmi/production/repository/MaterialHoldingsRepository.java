package com.monmi.production.repository;

import com.monmi.production.domain.MaterialHoldings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialHoldingsRepository extends JpaRepository<MaterialHoldings, Integer> {
}
