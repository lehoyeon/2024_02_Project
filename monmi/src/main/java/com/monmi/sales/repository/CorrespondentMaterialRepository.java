package com.monmi.sales.repository;

import com.monmi.sales.domain.CorrespondentMaterial;
import com.monmi.sales.repository.search.CorrespondentMaterialSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorrespondentMaterialRepository extends JpaRepository<CorrespondentMaterial, String>, CorrespondentMaterialSearch {
}
