package com.monmi.service;

import com.monmi.domain.Department;
import com.monmi.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        for (Department department : departments) {
            System.out.println("ID: " + department.getDepartmentId() + ", name: " + department.getDepartmentName());
        }
        return departments;
    }
}

