package com.comtrade.helloworld.services;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class HelloWorldService {

    public String helloPage(Model modelMessage) {
        modelMessage.addAttribute("message", "Hello World!");
        return "hello";
    }
}
