package com.monmi.controller;
import com.monmi.domain.Department;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import com.monmi.service.DepartmentService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;


    // HTML 페이지를 반환하는 엔드포인트
    @GetMapping("/accession")
    public String showPage() {
        return "accession";                             // accession.jsp 파일을 반환
    }

    // JSON 데이터를 반환하는 엔드포인트
    @GetMapping("/accession/data")
    @ResponseBody
    public List<Department> getDepartments() {
        return departmentService.getAllDepartments();   // JSON 데이터 반환
    }


    @PostMapping("/sumbit")
    public String handleFormSubmission(@RequestParam("deft_select") Integer departmentId) {
        System.out.println("선택된 부서 ID: " + departmentId);
        return "redirect:/success";                     // 성공 페이지로 리다이렉트
    }
}

