package com.monmi.service;

import com.monmi.domain.Department;
import com.monmi.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    // ✅ 전체 부서 목록 조회 (이미 존재)
    public List<Department> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        for (Department department : departments) {
            System.out.println("ID: " + department.getDepartmentId() + ", name: " + department.getDepartmentName());
        }
        return departments;
    }

    // ✅ 특정 부서 조회 메서드 추가
    public Department getDepartmentById(Integer departmentId) {
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 부서 ID입니다."));
    }
}
