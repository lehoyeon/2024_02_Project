package com.monmi.sales.service;

import com.monmi.dto.PageRequestDTO;
import com.monmi.dto.PageResponseDTO;
import com.monmi.sales.dto.CorrespondentProductDTO;

public interface CorrespondentProductService {
    // 거래처 목록 읽어오기 메소드
    PageResponseDTO correspondent_product_list(PageRequestDTO pageRequestDTO);
    void correspondent_product_register(CorrespondentProductDTO correspondentProductDTO);
    void correspondent_product_delete(String company_name);
    void correspondent_product_modify(CorrespondentProductDTO correspondentProductDTO);
}
