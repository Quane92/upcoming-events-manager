package com.zawartkawoj.upcomingevents.controller;

import com.zawartkawoj.upcomingevents.entity.Account;
import com.zawartkawoj.upcomingevents.dto.AccountDto;
import com.zawartkawoj.upcomingevents.service.AccountService;
import com.zawartkawoj.upcomingevents.validation.RegistrationValidationGroup;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/register")
    public ModelAndView showRegisterPage(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        if(authentication != null && authentication.isAuthenticated()) {
            modelAndView.setViewName("homeSignedIn.html");
        } else {
            modelAndView.addObject("account", new Account());
            modelAndView.setViewName("register.html");
        }
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView registerAccount(
            @ModelAttribute AccountDto accountDto,
            @Validated(RegistrationValidationGroup.class) Account account,
            BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("register.html");
        } else {
            modelAndView.setViewName("redirect:/home");
            accountService.addAccountThroughRegistration(accountDto);
        }
        return modelAndView;
    }

}