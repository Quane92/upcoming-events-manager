package com.zawartkawoj.upcomingevents.controller;

import com.zawartkawoj.upcomingevents.entity.Account;
import com.zawartkawoj.upcomingevents.entity.AccountDto;
import com.zawartkawoj.upcomingevents.service.AccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/register")
    public ModelAndView showRegisterPage() {
        ModelAndView modelAndView = new ModelAndView("register.html");
        modelAndView.addObject("account", new Account());
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView registerAccount(@ModelAttribute AccountDto accountDto) {
        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        accountService.addAccountThroughRegistration(accountDto);
        return modelAndView;
    }

}