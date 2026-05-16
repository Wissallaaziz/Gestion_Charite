package com.example.spring.controllers;

import com.example.spring.entities.ActionCharite;
import com.example.spring.services.ActionChariteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ActionChariteService actionService;

    @GetMapping("/")
    public String home(Model model) {
        List<ActionCharite> campaigns = actionService.getAll().stream().limit(3).toList();
        model.addAttribute("featuredCampaigns", campaigns);
        return "index";
    }

    @GetMapping("/categories")
    public String categories() {
        return "categories";
    }
}