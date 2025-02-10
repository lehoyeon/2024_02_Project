package com.monmi.sales.repository.search;

import com.monmi.sales.domain.CorrespondentMaterial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CorrespondentMaterialSearch {
    Page<CorrespondentMaterial> correspondent_material_search_all(String[] types, String keyword, Pageable pageable);
}
