package com.comtrade.helloworld.controller;

import com.comtrade.helloworld.services.ExternalAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.io.IOException;

@Controller
@Profile("externalAPI")
public class ExternalAPIController {

    @Autowired
    private final ExternalAPIService externalAPIService;

    @Autowired
    public ExternalAPIController(ExternalAPIService externalAPIService) {
        this.externalAPIService = externalAPIService;
    }

    @GetMapping("/hello/{language}")
    public String getTranslationsFromAPI(Model modelMessage, @PathVariable(value = "language") String language) throws IOException, InterruptedException {
        return externalAPIService.getTranslationsFromAPI(modelMessage, language);
    }
}
