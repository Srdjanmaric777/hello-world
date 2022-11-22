package com.comtrade.helloworld.controller;

import com.comtrade.helloworld.services.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldController {

    private final HelloWorldService helloWorldService;

    @Autowired
    public HelloWorldController(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @GetMapping("/hello")
    public String helloPage(Model modelMessage) {
        return helloWorldService.helloPage(modelMessage);
    }

    @GetMapping("/")
    public String defaultPage(Model modelMessage) {
        return helloWorldService.helloPage(modelMessage);
    }
}
