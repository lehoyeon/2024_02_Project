package com.monmi.sales.mapper;

import com.monmi.sales.domain.CorrespondentMaterial;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CorrespondentMaterialMapper {
    void correspondent_material_insert(CorrespondentMaterial correspondentMaterial);
    void correspondent_material_delete(String company_name);
    void correspondent_material_modify(CorrespondentMaterial correspondentMaterial);
}
