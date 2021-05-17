package com.casestudy.controller;

import com.casestudy.model.User;
import com.casestudy.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

public class UserController {
    @Autowired
    private AppUserService userService;

    @GetMapping("/create-user")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/views/simple-signup");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @PostMapping("/create-user")
    public ModelAndView saveUser(@Validated @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("/views/simple-signup");
        }

        userService.save(user);
        ModelAndView modelAndView = new ModelAndView("redirect:home");
//        modelAndView.addObject("city", new City());
        modelAndView.addObject("success", "New city created successfully");
        return modelAndView;
    }
}
