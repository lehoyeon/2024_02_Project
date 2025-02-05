package com.monmi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.monmi.service.noticeservice;
import com.monmi.domian.notice;

@Controller
public class noticecontroller {


    @Autowired
    private noticeservice noticeService;

    @GetMapping("/notices/{title}")
    public String getNoticeByTitle(@PathVariable String title, Model model) {
        notice notice = noticeService.getNoticeByTitle(title);
        model.addAttribute("notice", notice);
        return "notice_details"; // 템플릿 이름과 일치하는지 확인
    }

}