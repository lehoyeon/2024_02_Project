package com.monmi.sales.service;

import com.monmi.dto.PageRequestDTO;
import com.monmi.dto.PageResponseDTO;
import com.monmi.sales.dto.CorrespondentMaterialDTO;
import com.monmi.sales.dto.CorrespondentMaterialListDTO;

public interface CorrespondentMaterialService {
    // 거래처 목록 읽어오기 메소드
    PageResponseDTO correspondent_material_list(PageRequestDTO pageRequestDTO);
    CorrespondentMaterialListDTO correspondent_material_material_list();
    void correspondent_material_register(CorrespondentMaterialDTO correspondentMaterialDTO);
    void correspondent_material_delete(String company_name);
    void correspondent_material_modify(CorrespondentMaterialDTO correspondentMaterialDTO);
}
