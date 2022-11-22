package com.comtrade.helloworld.services;

import com.comtrade.helloworld.model.HelloWorld;
import com.comtrade.helloworld.repository.HelloWorldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class DBService {

    private final HelloWorldRepository repository;

    @Autowired
    public DBService(HelloWorldRepository repository) {
        this.repository = repository;
    }

    public String getTranslationsFromDatabase(Model modelMessage, @PathVariable(value = "language") String language) {

        Optional<HelloWorld> findLanguage = repository.findByLanguage(language);
        if(findLanguage.isPresent()) {
            modelMessage.addAttribute("message", findLanguage.get().getWord());
        } else {
            modelMessage.addAttribute("message", "Language is not defined!");
        }

        return "hello";
    }
}
