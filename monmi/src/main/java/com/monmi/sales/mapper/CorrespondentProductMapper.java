package com.monmi.sales.mapper;

import com.monmi.sales.domain.CorrespondentProduct;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CorrespondentProductMapper {
    void correspondent_product_insert(CorrespondentProduct correspondentProduct);
    void correspondent_product_delete(String company_name);
    void correspondent_product_modify(CorrespondentProduct correspondentProduct);
}
