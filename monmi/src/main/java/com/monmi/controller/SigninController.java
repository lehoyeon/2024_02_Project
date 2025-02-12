package com.monmi.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import com.monmi.dto.SigninDTO;
import com.monmi.service.SigninService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/accession")
public class SigninController {

    private final SigninService signinService;

    public SigninController(SigninService signinService) {
        this.signinService = signinService;
    }
    // ✅ 회원가입 페이지 이동
    @GetMapping
    public String showPage() {
        return "accession"; // accession.jsp 반환
    }
    @PostMapping("/register")
    public String registerUser(@ModelAttribute @Valid SigninDTO signinDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", result.getFieldError().getDefaultMessage());
            return "redirect:/accession"; // 다시 회원가입 페이지로 이동
        }
        try {
            signinService.registerUser(signinDTO);
            redirectAttributes.addFlashAttribute("message", "회원가입이 완료되었습니다!");
            return "redirect:/main"; // 메인 페이지로 이동
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage()); // "ID를 입력해야 합니다."
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage()); // "이미 사용 중인 ID입니다."
        }
        return "redirect:/accession"; // 실패 시 다시 회원가입 페이지로 리디렉션
    }






//    @PostMapping("/register")
//    public String registerUser(@ModelAttribute @Valid SigninDTO signinDTO, BindingResult result, RedirectAttributes redirectAttributes) {
//        if (result.hasErrors()) {
//            redirectAttributes.addFlashAttribute("message", result.getFieldError().getDefaultMessage());
//            return "redirect:/accession";
//        }
//        try {
//            signinService.registerUser(signinDTO);
//            redirectAttributes.addFlashAttribute("message", "회원가입이 완료되었습니다!");
//            return "redirect:/main";
//        } catch (IllegalArgumentException e) {
//            redirectAttributes.addFlashAttribute("message", e.getMessage()); // "ID를 입력해야 합니다."
//        } catch (IllegalStateException e) {
//            redirectAttributes.addFlashAttribute("message", e.getMessage()); // "이미 사용 중인 ID입니다."
//        }
//
//        return "redirect:/accession"; // 실패해도 같은 페이지로 리디렉션
//    }



}
