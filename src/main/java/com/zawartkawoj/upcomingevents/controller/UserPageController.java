package com.zawartkawoj.upcomingevents.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserPageController {

    @GetMapping("/user")
    public ModelAndView modelAndView() {
        ModelAndView modelAndView = new ModelAndView("accountPage.html");
        return modelAndView;
    }
}
