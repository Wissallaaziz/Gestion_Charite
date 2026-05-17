package com.example.spring.controllers;

import com.example.spring.entities.Utilisateur;
import com.example.spring.services.DashboardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        Utilisateur currentUser = (Utilisateur) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }
        if (!"ADMIN".equalsIgnoreCase(String.valueOf(currentUser.getRole()))) {
            return "redirect:/";
        }

        Double totalDons = dashboardService.totalDons();
        long totalActions = dashboardService.totalActions();
        long totalOrganisations = dashboardService.totalOrganisationsValides();
        long totalParticipations = dashboardService.totalParticipations();

        model.addAttribute("totalDons", totalDons != null ? totalDons : 0);
        model.addAttribute("totalActions", totalActions);
        model.addAttribute("totalOrganisationsValides", totalOrganisations);
        model.addAttribute("totalParticipations", totalParticipations);

        model.addAttribute("labels", List.of("Dons","Actions","Organisations","Participations"));
        model.addAttribute("values", List.of(
                totalDons != null ? totalDons : 0,
                (double) totalActions,
                (double) totalOrganisations,
                (double) totalParticipations
        ));

        return "admindashboard";
    }

    @GetMapping("/admin/global-campaigns")
    public String globalCampaignDirectory(HttpSession session) {
        Utilisateur currentUser = (Utilisateur) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }
        if (!"ADMIN".equalsIgnoreCase(String.valueOf(currentUser.getRole()))) {
            return "redirect:/";
        }
        return "global-campaign-directory";
    }
}