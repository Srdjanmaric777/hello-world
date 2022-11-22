package com.comtrade.helloworld.controller;

import com.comtrade.helloworld.model.HelloWorld;
import com.comtrade.helloworld.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@Profile("database")
public class SecurityController {

    private final SecurityService securityService;

    @Autowired
    public SecurityController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping("/secure/hello")
    public String securePage() {
        return securityService.securePage();
    }

    @GetMapping("/login")
    public String loginPage() {
        return securityService.loginPage();
    }

    @GetMapping("/admin")
    public String getAdminPage(Model modelMessage, HelloWorld helloWorld) {
        return securityService.getAdminPage(modelMessage, helloWorld);
    }

    @PostMapping ("/admin")
    public String postAdminPage(Model modelMessage, @ModelAttribute("message") HelloWorld helloWorld) {
        return securityService.postAdminPage(modelMessage, helloWorld);
    }
}
