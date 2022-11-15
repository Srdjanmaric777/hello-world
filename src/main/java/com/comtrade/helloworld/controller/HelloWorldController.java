package com.comtrade.helloworld.controller;

import com.comtrade.helloworld.model.HelloWorld;
import com.comtrade.helloworld.repository.HelloWorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Controller
public class HelloWorldController {

    @Autowired
    HelloWorldRepository repo;

    @GetMapping("/hello")
    public String helloPage(Model modelMessage) {
        modelMessage.addAttribute("message", "Hello World");
        return "hello";
    }

    @GetMapping("/hello/{language}")
    public String getHelloPage(Model modelMessage, @PathVariable(value = "language") String language) {
        String word = "";
        for (HelloWorld helloWorld : repo.findAll()) {
            if(helloWorld.getLanguage().equalsIgnoreCase(language)) {
                word = helloWorld.getWord();
                break;
            }
        }

        if(!word.isEmpty()){
            modelMessage.addAttribute("message", word);
        }else{
            modelMessage.addAttribute("message", "Language is not defined");
        }

        return "hello";
    }
}
