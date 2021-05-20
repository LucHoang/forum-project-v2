package com.casestudy.controller;

import com.casestudy.model.Category;
import com.casestudy.model.Hastag;
import com.casestudy.model.Topic;
import com.casestudy.model.User;
import com.casestudy.service.category.CategoryService;
import com.casestudy.service.hastag.HastagService;
import com.casestudy.service.topic.TopicService;
import com.casestudy.service.user.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Pageable;

import java.util.*;


@Controller
public class HomePageController {
    @Autowired
    private TopicService topicService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private HastagService hastagService;

    @Autowired
    private AppUserService userService;


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

    @ModelAttribute
    private Map<Long,List<Hastag>> listHastagWithTopicId(){
        Map<Long,List<Hastag>> listHastagWithTopicId = new HashMap<>();
        for (Topic topic: topicService.findAll()) {
            listHastagWithTopicId.put(topic.getTopicId(),topic.getHastags());
        }
        return  listHastagWithTopicId;
    }

    @GetMapping(value = {"/"})
    public ModelAndView listTopic(@PageableDefault(sort = {"title"}, value = 3) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("/views/index");
        modelAndView.addObject("categories",categories());
        modelAndView.addObject("hastags",hastags());
        modelAndView.addObject("listHastagWithTopicId", listHastagWithTopicId());
        modelAndView.addObject("topics", topicService.findAll(pageable));
        Optional<User> userCurrent = userService.findByUsername(getPrincipal());
        if(userCurrent.isPresent()){
            modelAndView.addObject("userCurrent", userCurrent.get());
        }
        return modelAndView;
    }

    @GetMapping(value = {"/get-topic-by-cate/{id}"})
    public ModelAndView getListByCategory(@PathVariable String id,@PageableDefault(sort = {"title"},size = 3, value = 3) Pageable pageable) {
        ModelAndView modelAndView;
        try {
            modelAndView = new ModelAndView("/views/index");
            modelAndView.addObject("topics", topicService.findByCategoryCateId(Long.parseLong(id),pageable));
            modelAndView.addObject("categories",categories());
            modelAndView.addObject("hastags",hastags());
        }catch (Exception e){  modelAndView = new ModelAndView("/views/404");}
        return modelAndView;
    }

    @GetMapping(value = {"/login"})
    public ModelAndView test() {
        ModelAndView modelAndView = new ModelAndView("/views/signin");
        return modelAndView;
    }

    @GetMapping(value = {"/login_error"})
    public ModelAndView loginError() {
        ModelAndView modelAndView = new ModelAndView("/views/signin");
        modelAndView.addObject("error", "Incorrect username or password !!!");
        return modelAndView;
    }
}

