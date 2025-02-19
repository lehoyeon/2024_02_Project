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
}
