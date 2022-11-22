package com.comtrade.helloworld.services;

import org.springframework.stereotype.Service;

@Service
public class HelloWorldRestService {

    public String HelloWorld() {
        return "Hello World!";
    }
}
