package com.casestudy.controller;

import com.casestudy.model.Topic;
import com.casestudy.service.topic.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Pageable;


@Controller
public class HomePageController {
    @Autowired
    private TopicService topicService;

    @GetMapping(value = {"/"})
    public ModelAndView listTopic(@PageableDefault(sort = {"title"}, value = 3) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/views/index");
        modelAndView.addObject("topics", topicService.findAll(pageable));
        return modelAndView;
    }
}
