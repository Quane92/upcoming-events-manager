package com.zawartkawoj.upcomingevents.controller;

import com.zawartkawoj.upcomingevents.entity.Account;
import com.zawartkawoj.upcomingevents.service.AccountService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserPageController {

    AccountService accountService;

    public UserPageController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/user")
    public ModelAndView modelAndView(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("accountPage.html");
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            Account account = accountService.findByEmail(authentication.getName());
            modelAndView.addObject("username", (account.getFirstName() + " " + account.getLastName()));
        }
        return modelAndView;
    }
}
