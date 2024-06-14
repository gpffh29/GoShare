package com.GoShare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class introduceController {
    @GetMapping("/introducePage")
    public String showIntroducePage() {
        return "boards/introducePage"; // introducePage.html로 이동
    }
}
