package com.casestudy.controller;

import com.casestudy.model.Category;
import com.casestudy.model.Hastag;
import com.casestudy.model.Topic;
import com.casestudy.model.User;
import com.casestudy.repository.ITopicRepository;
import com.casestudy.service.category.ICategoryService;
import com.casestudy.service.topic.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class TopicController {
    @Autowired
    private ITopicService topicService;

    @Autowired
    private ICategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> categories() {
        return categoryService.findAll();
    }

    @GetMapping("/create-topic")
    public ModelAndView showCreateTopic() {

        ModelAndView modelAndView = new ModelAndView("/views/create-tes");
        modelAndView.addObject("topic", new Topic());
        return modelAndView;
    }

    @PostMapping("/create-topic")
    public ModelAndView saveTopic(@Validated @ModelAttribute("topic") Topic topic, BindingResult bindingResult) {
//        if (bindingResult.hasFieldErrors()) {
//            return new ModelAndView("/views/create-tes");
//        }

        ;
//        Object principal1 = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        topic.getUser().setId(topic.getUser().getId());
        topic.setTopicDate(LocalDateTime.now());
        topicService.save(topic);
        ModelAndView modelAndView = new ModelAndView("/views/create-tes");
        modelAndView.addObject("topic", new Topic());
        modelAndView.addObject("message", "New topic created successfully");
        return modelAndView;
    }

    @GetMapping("/index-tes")
    public ModelAndView showUser() {
        ModelAndView modelAndView = new ModelAndView("/views/index-tes");
        modelAndView.addObject("user", new User() );
        return modelAndView;
    }

    public User getCurrentUser(){
        Optional<User> user;
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            userName = ((UserDetails) principal).getUsername();
        }else {
            userName = principal.toString();
        }
        if(userRepository.existsByUsername(userName)){
            user = userService.findByUsername(userName);
        } else {
            user = Optional.of(new User());
            user.get().setUsername("Anonymous");
        }
        return user.get();
    }

}
