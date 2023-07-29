package com.zawartkawoj.upcomingevents.controller;

import com.zawartkawoj.upcomingevents.service.AccountService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {

    AccountService accountService;

    public HomeController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ModelAndView showHomePage() {
        ModelAndView modelAndView = new ModelAndView("home.html");
        modelAndView.addObject("message", "Welcome to EventReminder");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView showAccountPage(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            modelAndView.addObject("username", userDetails.getUsername());
        }
        return new ModelAndView("redirect:/user");
    }
}
