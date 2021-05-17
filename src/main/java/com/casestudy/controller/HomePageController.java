package com.casestudy.controller;

import com.casestudy.service.topic.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomePageController {
    @Autowired
    private TopicService topicService;

    @GetMapping(value = {"/"})
    public ModelAndView listTopic() {
        ModelAndView modelAndView = new ModelAndView("/views/index");
        modelAndView.addObject("topics", topicService.findAll());
        return modelAndView;
    }
}
