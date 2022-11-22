package com.comtrade.helloworld.services;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class IndexService {

    public String error(Model modelMessage) {
        modelMessage.addAttribute("message", "Invalid Endpoint");
        return "error";
    }

    public String getAdminErrorPage(Model modelMessage) {
        modelMessage.addAttribute("message", "Only admin can access this page!");
        return "error403";
    }

    public String postAdminErrorPage() {
        return "redirect:/secure/hello";
    }
}
