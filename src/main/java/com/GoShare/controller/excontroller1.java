package com.GoShare.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class excontroller1 {
    @GetMapping(value="/ex1")
    public String ex1(){
        return "intro";
    }
}
