package com.zawartkawoj.upcomingevents.controller;

import com.zawartkawoj.upcomingevents.entity.Account;
import com.zawartkawoj.upcomingevents.service.AccountService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {

    AccountService accountService;

    public HomeController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ModelAndView showHomePage(@RequestParam(value = "error", required = false) String error,  Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("account", new Account());
        if (error != null) {
            modelAndView.addObject("error", error);
        }
        String viewName =
                authentication != null && authentication.isAuthenticated() ? "homeSignedIn.html" : "homeSignedOut.html";
        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    @PostMapping
    public ModelAndView showAccountPage(Account account) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/user");
        return modelAndView;
    }
}
