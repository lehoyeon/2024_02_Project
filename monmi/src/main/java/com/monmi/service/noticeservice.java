package com.monmi.service;


import com.monmi.domian.notice;
import com.monmi.repository.noticerepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class noticeservice {

    @Autowired
    private noticerepository noticeRepository;

    public notice getNoticeByTitle(String title) {
        return noticeRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("공지사항을 찾을 수 없습니다."));
    }
}