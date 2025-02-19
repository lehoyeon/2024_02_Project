package com.monmi.repository;

import com.monmi.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    Optional<Notice> findByTitle(String title);

    // 중요 공지사항만 가져오는 메서드 추가
    List<Notice> findByImportantTrue(); // 중요 공지사항만 반환

    // 중요 공지사항이 아닌 공지사항만 가져오는 메서드
    List<Notice> findByImportantFalse(); // 중요 공지사항 아닌 것만 반환
}