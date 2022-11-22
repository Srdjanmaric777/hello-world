package com.comtrade.helloworld.controller;

import com.comtrade.helloworld.services.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController implements ErrorController {

    private static final String PATH = "/error";
    private final IndexService indexService;

    @Autowired
    public IndexController(IndexService indexService) {
        this.indexService = indexService;
    }

    @RequestMapping(value = PATH)
    public String error(Model modelMessage) {
        return indexService.error(modelMessage);
    }

    @GetMapping("/error403")
    public String getAdminErrorPage(Model modelMessage) {
        return indexService.getAdminErrorPage(modelMessage);
    }

    @PostMapping("/error403")
    public String postAdminErrorPage() {
        return indexService.postAdminErrorPage();
    }
}
