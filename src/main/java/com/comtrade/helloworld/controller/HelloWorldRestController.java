package com.comtrade.helloworld.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldRestController {
    @GetMapping("/hello-rest")
    public String HelloWorld() {
        return "Hello World";
    }
}
