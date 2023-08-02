package com.zawartkawoj.upcomingevents.controller;

import com.zawartkawoj.upcomingevents.dto.EventDto;
import com.zawartkawoj.upcomingevents.entity.Account;
import com.zawartkawoj.upcomingevents.entity.Event;
import com.zawartkawoj.upcomingevents.service.AccountService;
import com.zawartkawoj.upcomingevents.service.EventService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserPageController {

    AccountService accountService;
    EventService eventService;

    public UserPageController(AccountService accountService, EventService eventService) {
        this.accountService = accountService;
        this.eventService = eventService;
    }

    @GetMapping("/user")
    public ModelAndView showUserPage(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("accountPage.html");
        if (authentication != null && authentication.isAuthenticated()) {
            Account account = accountService.findByEmail(authentication.getName());
            modelAndView.addObject("account", account);
            modelAndView.addObject("username", account.getFirstName() + " " + account.getLastName());
        }
        return modelAndView;
    }

    @GetMapping("/updateEvents")
    public ModelAndView showEventsForm() {
        ModelAndView modelAndView = new ModelAndView("/updateEvents.html");
        modelAndView.addObject("event", new Event());
        return modelAndView;
    }

    @PostMapping("/updateEvents")
    public ModelAndView postEvent(@ModelAttribute EventDto eventDto, Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("redirect:/user");
        Account account = accountService.findByEmail(authentication.getName());
        Event event = eventService.addOrUpdateEvent(eventDto);
        accountService.addOrUpdateEvent(account.getEmail(), event);
        return modelAndView;
    }
}
