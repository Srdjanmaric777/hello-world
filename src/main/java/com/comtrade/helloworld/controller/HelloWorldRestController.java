package com.comtrade.helloworld.controller;

import com.comtrade.helloworld.services.HelloWorldRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldRestController {

    private final HelloWorldRestService helloWorldRestService;

    @Autowired
    public HelloWorldRestController(HelloWorldRestService helloWorldRestService) {
        this.helloWorldRestService = helloWorldRestService;
    }
    @GetMapping("/hello-rest")
    public String HelloWorld() {
        return helloWorldRestService.HelloWorld();
    }
}
