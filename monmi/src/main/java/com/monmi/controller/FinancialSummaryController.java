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

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sales_management")
public class FinancialSummaryController {

    @Autowired
    private FinancialSummaryRepository repository;


    @GetMapping
    public String showSalesManagementPage(
            @RequestParam(defaultValue = "0") int page,  // 페이지 번호 (0부터 시작)
            @RequestParam(required = false) String month, // 사용자가 선택한 월 (01, 02, 03 형식)
            Model model) {

        // 2024년 데이터만 필터링
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 12, 31);
        Pageable pageable = PageRequest.of(page, 10);
        Page<FinancialSummary> financialPage;

        // ✅ 선택한 월이 유효한 숫자인지 확인 후 처리
        if (month != null && !month.isEmpty()) {
            try {
                int monthInt = Integer.parseInt(month); // "02" -> 2 변환

                if (monthInt >= 1 && monthInt <= 12) { // ✅ 유효한 월인지 검사
                    financialPage = repository.findByMonth(2024, monthInt, pageable);
                } else {
                    financialPage = repository.findByRecordDateBetween(startDate, endDate, pageable);
                }
            } catch (NumberFormatException e) {
                financialPage = repository.findByRecordDateBetween(startDate, endDate, pageable);
            }
        } else {
            financialPage = repository.findByRecordDateBetween(startDate, endDate, pageable);
        }

        // ✅ 월별 차트 데이터 생성 (전체 데이터를 기준으로)
        List<FinancialSummary> allData = repository.findByRecordDateBetween(startDate, endDate);
        Map<String, Double> monthlyRevenue = new LinkedHashMap<>();
        Map<String, Double> monthlyProfit = new LinkedHashMap<>();

        for (FinancialSummary data : allData) {
            String monthKey = String.format("%02d", data.getRecordDate().getMonthValue()); // 2 -> "02"
            monthlyRevenue.put(monthKey, monthlyRevenue.getOrDefault(monthKey, 0.0) + data.getTotalRevenue());
            monthlyProfit.put(monthKey, monthlyProfit.getOrDefault(monthKey, 0.0) + data.getNetProfit());
        }

        // ✅ 모델에 데이터 추가
        model.addAttribute("financialData", financialPage.getContent()); // 현재 페이지 데이터
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", financialPage.getTotalPages());
        model.addAttribute("selectedMonth", month); // 선택한 월 유지

        // ✅ 차트 데이터 추가
        model.addAttribute("monthlyRevenue", monthlyRevenue);
        model.addAttribute("monthlyProfit", monthlyProfit);

        return "sales_management";
    }
}
