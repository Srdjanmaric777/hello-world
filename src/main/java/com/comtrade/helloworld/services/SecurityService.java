package com.comtrade.helloworld.services;

import com.comtrade.helloworld.model.HelloWorld;
import com.comtrade.helloworld.repository.HelloWorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

@Service
public class SecurityService {

    private final HelloWorldRepository repository;

    @Autowired
    public SecurityService(HelloWorldRepository repository) {
        this.repository = repository;
    }

    public String securePage() {
        return "securehello";
    }

    public String loginPage() {
        return "login";
    }

    public String getAdminPage(Model modelMessage, HelloWorld helloWorld) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            modelMessage.addAttribute("message", helloWorld);
            return "admin";
        }
        else {
            return "redirect:/error403";
        }
    }

    public String postAdminPage(Model modelMessage1, @ModelAttribute("message") HelloWorld helloWorld) {

        Optional<HelloWorld> language = repository.findByLanguage(helloWorld.getLanguage());
        if(language.isPresent()) {
            modelMessage1.addAttribute("message1", "Language is already in database");
            return "admin";
        }
        repository.save(helloWorld);
        return "redirect:/admin";
    }
}
