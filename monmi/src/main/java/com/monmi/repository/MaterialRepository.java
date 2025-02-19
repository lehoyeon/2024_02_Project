package com.monmi.repository;

import com.monmi.domain.Material;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

@Mapper
public interface MaterialRepository extends JpaRepository<Material, Integer> {
}
