package com.comtrade.helloworld.controller;

import com.comtrade.helloworld.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Profile("database")
public class DBController {

    private final DBService databaseService;

    @Autowired
    public DBController(DBService databaseService) {
        this.databaseService = databaseService;
    }

    @GetMapping("/hello/{language}")
    public String getHelloPage(Model modelMessage, @PathVariable(value = "language") String language) {
        return databaseService.getTranslationsFromDatabase(modelMessage, language);
    }
}
