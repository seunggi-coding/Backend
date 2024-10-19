package com.farm.smartfarm.controller;

import com.farm.smartfarm.domain.User;
import com.farm.smartfarm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/setUser")
    public String setUserForm(Model model, Authentication authentication) {
        String currentPrincipalName = authentication.getName();
        User user = userService.findByEmail(currentPrincipalName);

        model.addAttribute("name", user.getName());
        model.addAttribute("email", user.getEmail());

        return "user/setUser";
    }

}
