package com.casestudy.controller;

import com.casestudy.model.*;
import com.casestudy.service.category.ICategoryService;
import com.casestudy.service.hastag.HastagService;
import com.casestudy.service.hastag.IHastagService;
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
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller

public class TopicController {

    @Autowired
    private ITopicService topicService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IReplyService replyService;

    @Autowired
    private AppUserService userService;

    @Autowired
    private HastagService hastagService;

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

    @GetMapping("/detail/{id}")
    public ModelAndView showDetailTopic(@PathVariable Long id){
        Optional<Topic> topic = topicService.findById(id);
        Iterable<Reply> reply = replyService.findAllByTopic(topic.get());
        Iterable<Topic> topTopics = topicService.findTopByTopicLike();
        topic.get().setTopicView(topic.get().getTopicView()+1); // tang moi khi an vao detail topic
        topicService.save(topic.get());
        if (topic.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/views/single-topic");
            modelAndView.addObject("topic", topic.get());
            modelAndView.addObject("topTopic", topTopics);
            modelAndView.addObject("replies", reply);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }

    }

    @GetMapping("/create-topic")
    public ModelAndView showCreateTopic(Model model) {
        Optional<User> userCurrent = userService.findByUsername(getPrincipal());
        ModelAndView modelAndView = new ModelAndView("/views/create-topic");
        modelAndView.addObject("userCurrent",userCurrent.get());
        modelAndView.addObject("topic", new Topic(userCurrent.get()));
        return modelAndView;
    }


    @PostMapping("/create-topic")
    public ModelAndView saveTopic(@Validated @ModelAttribute("topic") Topic topic,@RequestParam String inputHastag,BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView("/views/create-topic");
        String[] arrayHastag = inputHastag.trim().split(",");
        Set<Hastag> hastagSet = new HashSet<>();
        System.out.println("inputHastag:" +inputHastag);
        boolean checkHastag = true,checkTopic = true;
        // Check độ dài của mỗi hastag min = 1 ,max = 8, tối đa 5 hastag
        for (int i = 0; i < arrayHastag.length; i++) {
            if(arrayHastag[i].length() <1 || arrayHastag.length>8 || arrayHastag.length > 5){
                checkHastag = false;
                checkTopic = false;
                break;
            }
        }
        // Insert hastag mới
        for (int i = 0; i < arrayHastag.length; i++) {
            try {
                Hastag hastag = hastagService.saveAndReturn(new Hastag(arrayHastag[1]));
                hastagSet.add(hastag);
            }catch (Exception e){ checkHastag = false; checkTopic = false; break; }
        }
        // Insert topic mới
        if(checkTopic){
            topic.setHastags(hastagSet);
            topic.setTopicDate(LocalDateTime.now());
            topicService.save(topic);
        }

        Optional<User> userCurrent = userService.findByUsername(getPrincipal());

        modelAndView.addObject("userCurrent",userCurrent.get());
        modelAndView.addObject("topic", new Topic());
        modelAndView.addObject("checkTopic", checkTopic);
        modelAndView.addObject("checkHastag", checkHastag);
        return modelAndView;
    }
}
