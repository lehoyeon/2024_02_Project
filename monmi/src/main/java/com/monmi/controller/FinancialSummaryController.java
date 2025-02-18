package com.monmi.controller;

import com.monmi.domain.FinancialSummary;
import com.monmi.repository.FinancialSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller // 🔹 @RestController 대신 @Controller 사용
@RequestMapping("/sales_management")
public class FinancialSummaryController {

    @Autowired
    private FinancialSummaryRepository repository;

    @GetMapping
    public String showSalesManagementPage(
            @RequestParam(defaultValue = "0") int page,  // 🔹 페이지 번호 (0부터 시작)
            Model model) {

        // 🔹 2024년 데이터만 필터링
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);

        // 🔹 페이지 크기 = 10
        Pageable pageable = PageRequest.of(page, 10);
        Page<FinancialSummary> financialPage = repository.findByRecordDateBetween(startDate, endDate, pageable);

        // 🔹 전체 데이터를 가져와서 월별로 그룹화 (차트에서 사용)
        List<FinancialSummary> allData = repository.findByRecordDateBetween(startDate, endDate);
        Map<String, Double> monthlyRevenue = new LinkedHashMap<>();
        Map<String, Double> monthlyProfit = new LinkedHashMap<>();

        for (FinancialSummary data : allData) {
            String month = data.getRecordDate().getMonth().toString(); // "JANUARY", "FEBRUARY" 등

            monthlyRevenue.put(month, monthlyRevenue.getOrDefault(month, 0.0) + data.getTotalRevenue());
            monthlyProfit.put(month, monthlyProfit.getOrDefault(month, 0.0) + data.getNetProfit());
        }
        // ✅ [추가] 디버깅 로그 (데이터가 정상적으로 들어오는지 확인)
        System.out.println("📊 전체 데이터 개수: " + allData.size());
        System.out.println("📊 월별 순매출 데이터 확인: " + monthlyRevenue);
        System.out.println("📊 월별 순이익 데이터 확인: " + monthlyProfit);


        // 🔹 모델에 데이터 추가
        model.addAttribute("financialData", financialPage.getContent()); // 현재 페이지 데이터
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", financialPage.getTotalPages());

        // 🔹 월별 데이터 추가 (차트 용)
        model.addAttribute("monthlyRevenue", monthlyRevenue);
        model.addAttribute("monthlyProfit", monthlyProfit);

        return "sales_management";
    }




}


