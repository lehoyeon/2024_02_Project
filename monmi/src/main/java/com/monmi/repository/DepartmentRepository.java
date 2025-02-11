package com.monmi.repository;

import com.monmi.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    // JPQL 또는 Native SQL로 쿼리 작성

    List<Department> findAll();

    // CEO를 보여주지 않아야할때 사용.
    @Query("SELECT d FROM Department d WHERE d.departmentName != 'CEO'")
    List<Department> findAllExceptCEO();
}

