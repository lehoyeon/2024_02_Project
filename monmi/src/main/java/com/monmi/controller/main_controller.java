package com.monmi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
public class main_controller {

    @GetMapping("/main")
    public void main() {
        log.info("main..............");

    }

    @GetMapping("/elements")
    public void elemenets() {
        log.info("main..............");

    }

    @GetMapping("/generic")
    public void generic() {
        log.info("main..............");

    }

    @GetMapping("/landing")
    public void landing() {
        log.info("main..............");

    }

}
