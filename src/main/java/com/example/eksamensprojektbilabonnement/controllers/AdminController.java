package com.example.eksamensprojektbilabonnement.controllers;

import com.example.eksamensprojektbilabonnement.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @Autowired
    AdminService adminService;
    @GetMapping ("/admin_homepage")
    public String adminHomepage(Model model) {
        model.addAttribute("changeLogs", adminService.getChangeLogs());
        return "home/admin/admin_homepage";
    }
}
