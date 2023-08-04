package com.zawartkawoj.upcomingevents.controller;

import com.zawartkawoj.upcomingevents.service.AccountService;
import org.springframework.security.core.Authentication;
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
    public ModelAndView showHomePage(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        String viewName =
                authentication != null && authentication.isAuthenticated() ? "homeSignedIn.html" : "homeSignedOut.html";
        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    @PostMapping
    public ModelAndView showAccountPage() {
        ModelAndView modelAndView = new ModelAndView("redirect:/user");
        return modelAndView;
    }
}
