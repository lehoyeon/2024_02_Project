package com.monmi.controller;

import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller // 웹프로그램 만들 때
@Log4j2
public class SampleController {
    
    @GetMapping("/hello") // url이 /hello로 끝나면 해당 메소드가 실행
    public void hello(Model model) {
        log.info("hello...............");

        model.addAttribute("msg", "HELLO WORLD");
        // 마지막에는 /resources/templates/hello.html파일로 이동
    }


}
