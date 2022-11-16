package com.comtrade.helloworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/secure/hello")
    public String secureHelloPage(Model modelMessage) {
        modelMessage.addAttribute("message", "Secure Hello World");
        return "securehello";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
