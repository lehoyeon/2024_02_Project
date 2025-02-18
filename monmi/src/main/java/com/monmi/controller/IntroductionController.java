package com.monmi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
public class IntroductionController {

    @GetMapping("/introduction")
    public String introduction() {
        log.info("Rendering sales_management.html...");
        return "/introduction";
    }


}
