package com.monmi.repository;

import com.monmi.domain.Part;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

@Mapper
public interface PartRepository extends JpaRepository<Part, Long> {
}
