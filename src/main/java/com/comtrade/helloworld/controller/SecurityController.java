package com.comtrade.helloworld.controller;

import com.comtrade.helloworld.model.HelloWorld;
import com.comtrade.helloworld.repository.HelloWorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SecurityController {

    @Autowired
    HelloWorldRepository repo;

    @GetMapping("/secure/hello")
    public String secureHelloPage() {
        return "securehello";
    }

    @GetMapping("/login")
    public String loginHelloPage() {
        return "login";
    }

    @GetMapping("/admin")
    public String adminPage(Model modelMessage, HelloWorld helloWorld) {
        modelMessage.addAttribute("message", helloWorld);
        return "admin";
    }

    @PostMapping ("/admin")
    public String postAdminPage(@ModelAttribute("message") HelloWorld helloWorld) {
        repo.save(helloWorld);
        return "admin";
    }

}
