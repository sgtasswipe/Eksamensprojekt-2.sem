package com.example.eksamensprojektbilabonnement.controllers;

import com.example.eksamensprojektbilabonnement.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ValidateController {
    @Autowired
    EmployeeService employeeService;



    @PostMapping("/validate")

    public String validate(Model model, @RequestParam String email, @RequestParam String password, RedirectAttributes redirectAttributes) {
        String loginStatus = employeeService.checkPass(email,password);
        if (loginStatus.equals("UserApproved")) {

            redirectAttributes.addAttribute("email", email);
            return "redirect:/homepage";

        } else if (loginStatus.equals("NoUserFound")) {
            model.addAttribute("error", "This email does not exist in the database");
            return "home/login";
        } else {
            model.addAttribute("error", "Wrong password");
        }
        return "home/login";
    }
}