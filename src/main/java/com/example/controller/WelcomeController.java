package com.example.controller;

import com.example.model.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WelcomeController {

    @GetMapping("/welcome")
    public String welcome(Model model, RedirectAttributes redirectAttributes)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails currentUser = null;

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            currentUser = (CustomUserDetails) authentication.getPrincipal();
        }

        if (currentUser == null) {
            throw new NullPointerException("Current user is null");
        }

        model.addAttribute("user", currentUser.getUser());

        if ("ROLE_USER".equals(currentUser.getUser().getRole().name())) {
            return "redirect:/jpa/cars/view_cars_user";
        } else if ("ROLE_EDITOR".equals(currentUser.getUser().getRole().name()))
        {
            return "redirect:/jpa/cars/view_cars_editor";
        }

        return "welcome";
    }
}
