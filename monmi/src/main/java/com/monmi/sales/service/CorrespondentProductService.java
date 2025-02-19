package com.monmi.sales.service;

import com.monmi.dto.PageRequestDTO;
import com.monmi.dto.PageResponseDTO;
import com.monmi.sales.dto.CorrespondentProductDTO;

public interface CorrespondentProductService {
    PageResponseDTO correspondent_product_list(PageRequestDTO pageRequestDTO);
    void correspondent_product_register(CorrespondentProductDTO correspondentProductDTO);
    void correspondent_product_delete(String company_name);
    void correspondent_product_modify(CorrespondentProductDTO correspondentProductDTO);
}
