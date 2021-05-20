package com.casestudy.controller;

import com.casestudy.model.*;
import com.casestudy.service.category.CategoryService;
import com.casestudy.service.hastag.HastagService;
import com.casestudy.service.topic.TopicService;
import com.casestudy.service.user.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class DetailsController {
    @Autowired
    private TopicService topicService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private HastagService hastagService;
    @Autowired
    private AppUserService userService;
    @GetMapping("/detail-user/about")

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
    @ModelAttribute("userCurrent")
    public User userOptional(){
        return userService.findByUsername(getPrincipal()).get();
    }



    @ModelAttribute
    private List<Category> categories(){
        List<Category> categories = new ArrayList<>();
        categoryService.findAll().forEach(categories::add);
        return categories;
    }
    @ModelAttribute
    private List<Hastag> hastags(){
        List<Hastag> hastags = new ArrayList<>();
        hastagService.getTheMostUsedHashtags().forEach(hastags::add);
        return hastags;
    }

    @GetMapping("/detail-user")
    public ModelAndView showProfile() {
        ModelAndView modelAndView = new ModelAndView("/views/profile-about");
        return modelAndView;
    }

    @GetMapping("/detail-user/topic")
    public ModelAndView showTopic(@PageableDefault(sort = {"title"}, value = 3) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/views/profile-topic");
        Optional<User> userCurrent = userService.findByUsername(getPrincipal());
        modelAndView.addObject("userCurrent",userCurrent.get());
        modelAndView.addObject("topic", new Topic());
        modelAndView.addObject("topics", topicService.findAll(pageable));
        modelAndView.addObject("categories",categories());
        modelAndView.addObject("hastags",hastags());
        return modelAndView;
    }





}
