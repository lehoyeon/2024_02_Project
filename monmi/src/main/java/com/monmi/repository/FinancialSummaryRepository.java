package com.monmi.repository;

import com.monmi.domain.FinancialSummary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface FinancialSummaryRepository extends JpaRepository<FinancialSummary, Long> {

    // 2024년(1월 1일 ~ 12월 31일) 데이터만 필터링
    Page<FinancialSummary> findByRecordDateBetween(
            LocalDate startDate, LocalDate endDate, Pageable pageable
    );
    // 차트를 위한 모든 데이터 조회 메서드 (페이징 없이 전체 데이터)
    List<FinancialSummary> findByRecordDateBetween(LocalDate startDate, LocalDate endDate);

    // 특정 월 데이터 조회 메서드 추가
    @Query("SELECT f FROM FinancialSummary f WHERE YEAR(f.recordDate) = :year AND MONTH(f.recordDate) = :month")
    Page<FinancialSummary> findByMonth(@Param("year") int year, @Param("month") int month, Pageable pageable);
}
