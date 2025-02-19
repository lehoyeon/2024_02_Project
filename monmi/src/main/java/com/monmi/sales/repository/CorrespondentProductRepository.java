package com.monmi.sales.repository;

import com.monmi.sales.domain.CorrespondentProduct;
import com.monmi.sales.repository.search.CorrespondentProductSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorrespondentProductRepository extends JpaRepository<CorrespondentProduct, String>, CorrespondentProductSearch {
}
