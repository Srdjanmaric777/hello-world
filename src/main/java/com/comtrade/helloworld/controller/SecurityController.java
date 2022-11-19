package com.comtrade.helloworld.controller;

import com.comtrade.helloworld.model.HelloWorld;
import com.comtrade.helloworld.repository.HelloWorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping("/error403")
    public String errorPage(Model modelMessage) {
        modelMessage.addAttribute("message", "Only admin can access this page!");
        return "error403";
    }

    @PostMapping("/error403")
    public String postErrorPage() {
        return "redirect:/secure/hello";
    }

    @GetMapping("/admin")
    public String adminPage(Model modelMessage, HelloWorld helloWorld) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            modelMessage.addAttribute("message", helloWorld);
            return "admin";
        }
        else {
            return "redirect:/error403";
        }
    }

    @PostMapping ("/admin")
    public String postAdminPage(@ModelAttribute("message") HelloWorld helloWorld) {

        boolean mess = false;
        for (HelloWorld helloWorld1 : repo.findAll()) {
            if (helloWorld1.getLanguage().equalsIgnoreCase(helloWorld.getLanguage())
                    || helloWorld.getLanguage() == null
                    || helloWorld.getLanguage().equalsIgnoreCase("")
            ) {
                mess = true;
                break;
            }
        }

        if(!mess)
            repo.save(helloWorld);

        return "admin";
    }

}
