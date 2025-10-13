package com.AJP.demo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Maps to resources/templates/index.html via WebConfig
    // or directly here if WebConfig is not used.
    @GetMapping("/")
    public String home() {
        return "index";
    }
}