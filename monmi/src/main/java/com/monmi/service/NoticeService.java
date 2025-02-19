package com.monmi.service;

import com.monmi.domain.Notice;
import com.monmi.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    // 중요 공지사항 3개만 유지하는 메서드
    public void handleImportantNotices() {
        List<Notice> importantNotices = noticeRepository.findByImportantTrue();

        if (importantNotices.size() >= 3) {
            // 중요 공지사항이 3개 이상인 경우, 가장 오래된 것을 일반 공지사항으로 변경
            importantNotices.sort(Comparator.comparing(Notice::getCreatedAt)); // 오래된 순으로 정렬
            Notice oldestImportantNotice = importantNotices.get(0);
            oldestImportantNotice.setImportant(false); // 중요 공지사항에서 제외
            noticeRepository.save(oldestImportantNotice); // 변경된 공지사항 저장
        }
    }

    // 중요 공지사항을 3개만 가져오는 메서드
    public List<Notice> getImportantNotices() {
        // 중요 공지사항을 최신순으로 정렬하고, 3개만 반환
        return noticeRepository.findByImportantTrue().stream()
                .sorted((n1, n2) -> n2.getCreatedAt().compareTo(n1.getCreatedAt()))  // 최신순으로 정렬
                .limit(3)  // 3개만 가져오기
                .collect(Collectors.toList());
    }

    // 일반 공지사항을 날짜별로 가져오는 메서드 (최신순)
    public List<Notice> getNoticesSortedByDate() {
        List<Notice> notices = noticeRepository.findAll();
        notices.sort((n1, n2) -> n2.getCreatedAt().compareTo(n1.getCreatedAt()));  // 최신순으로 정렬
        return notices;
    }

    // 공지사항 ID로 하나의 공지사항 가져오는 메서드
    public Notice getNoticeById(Long id) {
        Optional<Notice> notice = noticeRepository.findById(id);
        return notice.orElseThrow(() -> new RuntimeException("공지사항을 찾을 수 없습니다."));  // 공지사항이 없을 경우 예외 처리
    }

    // 공지사항 저장하는 메서드
    public Notice saveNotice(Notice notice) {
        // 새 공지사항일 때는 createdAt을 설정
        if (notice.getId() == null) {
            notice.setCreatedAt(LocalDateTime.now());
            notice.setUpdatedAt(null);  // 새로 생성된 공지사항에는 updatedAt을 null로 설정
        } else {
            // 공지사항 수정 시 updatedAt을 설정
            notice.setUpdatedAt(LocalDateTime.now());
        }

        // 중요 공지사항인 경우 처리
        if (notice.getImportant() != null && notice.getImportant()) {
            handleImportantNotices(); // 중요 공지사항이 3개 초과하면 처리
        }

        // 공지사항 저장
        return noticeRepository.save(notice);
    }

    // 공지사항 삭제하는 메서드
    public void deleteNotice(Long id) {
        noticeRepository.deleteById(id);  // ID로 공지사항 삭제
    }
}