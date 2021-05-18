package com.casestudy.controller;

import com.casestudy.model.Category;
import com.casestudy.model.Hastag;
import com.casestudy.model.Topic;
import com.casestudy.service.category.CategoryService;
import com.casestudy.service.hastag.HastagService;
import com.casestudy.service.topic.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;


@Controller
public class HomePageController {
    @Autowired
    private TopicService topicService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private HastagService hastagService;

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

    @GetMapping(value = {"/"})
    public ModelAndView listTopic(@PageableDefault(sort = {"title"}, value = 3) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/views/index");
        modelAndView.addObject("topics", topicService.findAll(pageable));
        modelAndView.addObject("categories",categories());
        modelAndView.addObject("hastags",hastags());
        return modelAndView;
    }
}
