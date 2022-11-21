package com.comtrade.helloworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldController {

    @GetMapping("/hello")
    public String helloPage(Model modelMessage) {
        modelMessage.addAttribute("message", "Hello World!");
        return "hello";
    }
}
