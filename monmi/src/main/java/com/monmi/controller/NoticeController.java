package com.monmi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import com.monmi.service.NoticeService;
import com.monmi.domain.Notice;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/notice")
    public String getAllNotices(Model model) {
        List<Notice> importantNotices = noticeService.getImportantNotices();
        List<Notice> regularNotices = noticeService.getNoticesSortedByDate();

        model.addAttribute("importantNotices", importantNotices);
        model.addAttribute("regularNotices", regularNotices);

        return "Notice/notice";
    }
    @GetMapping("/test")
    public String getAllNotices1(Model model) {
        List<Notice> importantNotices = noticeService.getImportantNotices();
        List<Notice> regularNotices = noticeService.getNoticesSortedByDate();

        model.addAttribute("importantNotices", importantNotices);
        model.addAttribute("regularNotices", regularNotices);

        return "Notice/test";
    }

    @GetMapping("/notice/{id}")
    public String getNoticeDetails(@PathVariable Long id, Model model) {
        Notice notice = noticeService.getNoticeById(id);
        model.addAttribute("notice", notice);
        return "notice/notice_details";
    }

    @GetMapping("/notice/create")
    public String createNoticeForm(Model model) {
        model.addAttribute("notice", new Notice());
        return "Notice/create";
    }

    @PostMapping("/notice/create")
    public String createNotice(@ModelAttribute Notice notice) {
        noticeService.saveNotice(notice);
        return "redirect:/notice";
    }

    @GetMapping("/notice/{id}/edit")
    public String editNoticeForm(@PathVariable Long id, Model model) {
        Notice notice = noticeService.getNoticeById(id);
        model.addAttribute("notice", notice);
        return "Notice/edit";
    }

    @PostMapping("/notice/{id}/edit")
    public String updateNotice(@PathVariable Long id, @ModelAttribute Notice notice) {
        notice.setId(id);
        noticeService.saveNotice(notice);
        return "redirect:/notice";
    }

    @GetMapping("/notice/{id}/delete")
    public String deleteNotice(@PathVariable Long id) {
        noticeService.deleteNotice(id);
        return "redirect:/notice";
    }
}