package com.casestudy.controller;

import com.casestudy.model.Category;
import com.casestudy.model.Reply;
import com.casestudy.model.Topic;
import com.casestudy.model.User;
import com.casestudy.service.category.ICategoryService;
import com.casestudy.service.reply.IReplyService;
import com.casestudy.service.topic.ITopicService;
import com.casestudy.service.user.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Optional;
@Controller

public class TopicController {

    @Autowired
    private ITopicService topicService;

    @Autowired
    private ICategoryService categoryService;


    @Autowired
    private AppUserService userService;

    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }

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



    @GetMapping("/create-topic")
    public ModelAndView showCreateTopic(Model model) {
        Optional<User> userCurrent = userService.findByUsername(getPrincipal());
        ModelAndView modelAndView = new ModelAndView("/views/create-topic");
        modelAndView.addObject("userCurrent",userCurrent.get());
//        redirectAttributes.addFlashAttribute("user",getPrincipal());
        modelAndView.addObject("topic", new Topic(userCurrent.get()));
        return modelAndView;
    }


    @PostMapping("/create-topic")
    public ModelAndView saveTopic(@Validated @ModelAttribute("topic") Topic topic, BindingResult bindingResult) {
//        if (bindingResult.hasFieldErrors()) {
//            return new ModelAndView("/views/create-tes");
//        }
        topic.setTopicDate(LocalDateTime.now());
        topicService.save(topic);
        ModelAndView modelAndView = new ModelAndView("/views/create-topic");
        Optional<User> userCurrent = userService.findByUsername(getPrincipal());
        modelAndView.addObject("userCurrent",userCurrent.get());
        modelAndView.addObject("topic", new Topic());
        modelAndView.addObject("message", "New topic created successfully");
        return modelAndView;
    }
}
