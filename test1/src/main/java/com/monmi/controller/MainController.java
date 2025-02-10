package com.monmi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/main")
    public String main() {
        log.info("Rendering main.html...");
        return "main"; // resources/templates/main.html 렌더링
    }

    @GetMapping("/elements")
    public String elements() {
        log.info("Rendering elements.html...");
        return "elements"; // resources/templates/elements.html 렌더링
    }

    @GetMapping("/generic")
    public String generic() {
        log.info("Rendering generic.html...");
        return "generic"; // resources/templates/generic.html 렌더링
    }

    @GetMapping("/landing")
    public String landing() {
        log.info("Rendering landing.html...");
        return "landing"; // resources/templates/landing.html 렌더링
    }


}
