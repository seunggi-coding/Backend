package com.farm.smartfarm.controller.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class UserAuthController {

    @GetMapping("/login")
    public String loginForm() {
        return "user/login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/logout";
    }

    @GetMapping("/join")
    public String joinForm() {
        return "user/join";
    }

}
