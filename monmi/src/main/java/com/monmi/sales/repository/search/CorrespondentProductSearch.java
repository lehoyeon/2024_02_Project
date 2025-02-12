package com.monmi.sales.repository.search;

import com.monmi.sales.domain.CorrespondentProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CorrespondentProductSearch {
    Page<CorrespondentProduct> correspondent_product_search_all(String[] types, String keyword, Pageable pageable);
}
