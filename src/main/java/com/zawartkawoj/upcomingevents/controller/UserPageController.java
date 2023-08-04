package com.zawartkawoj.upcomingevents.controller;

import com.zawartkawoj.upcomingevents.dto.EventDto;
import com.zawartkawoj.upcomingevents.entity.Account;
import com.zawartkawoj.upcomingevents.entity.Event;
import com.zawartkawoj.upcomingevents.service.AccountService;
import com.zawartkawoj.upcomingevents.service.EventService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/event-form")
    public ModelAndView showEventsForm(@RequestParam(required = false) Integer id) {
        ModelAndView modelAndView = new ModelAndView("/updateEvents.html");
        modelAndView.addObject("event", id != null ? eventService.findById(id) : new Event());
        return modelAndView;
    }

    @PostMapping("/event-form")
    public ModelAndView postEvent(@ModelAttribute EventDto eventDto, Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("redirect:/user");

        Account account = accountService.findByEmail(authentication.getName());
        eventService.addOrUpdateEvent(eventDto, account);

        return modelAndView;
    }

    @GetMapping("/delete-event/{id}")
    public ModelAndView deleteEvent(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/user");
        eventService.deleteEventById(id);
        return modelAndView;
    }

}
