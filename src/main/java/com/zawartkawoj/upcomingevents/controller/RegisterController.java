package com.zawartkawoj.upcomingevents.controller;

import com.zawartkawoj.upcomingevents.entity.Account;
import com.zawartkawoj.upcomingevents.service.AccountServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private AccountServiceImpl accountServiceImpl;

    public RegisterController(AccountServiceImpl accountServiceImpl) {
        this.accountServiceImpl = accountServiceImpl;
    }

    @GetMapping
    public ModelAndView showRegisterPage() {
        ModelAndView modelAndView = new ModelAndView("register.html");
        modelAndView.addObject("account", new Account());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView registerAccount(@ModelAttribute Account account) {
        ModelAndView modelAndView = new ModelAndView("home.html");
        accountServiceImpl.addAccount(account);
        return modelAndView;
    }
}
