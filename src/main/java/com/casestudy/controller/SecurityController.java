package com.casestudy.controller;

import com.casestudy.model.Topic;
import com.casestudy.service.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class SecurityController {
    @Autowired
    private ITopicService topicService;
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

    @GetMapping(value = {"/"})
    public String listCity(Model model) {
        model.addAttribute("user", getPrincipal());
        return "views/index";
    }

    @GetMapping(value = {"/home"})
    public String Homepage(Model model) {
        model.addAttribute("user", getPrincipal());
        return "/views/index";
    }

    @GetMapping("/admin")
    public String adminPage(ModelMap modelMap) {
        modelMap.addAttribute("user", getPrincipal());
        return "/views/index";
    }

    @GetMapping("/accessDenied")
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "/access-denied";
    }

    @GetMapping( "/dba")
    public String dbaPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "redirect:cities";
    }
//    @GetMapping("/detail")
//    public String showDetailTopic(ModelMap modelMap) {
//        modelMap.addAttribute("topic", getPrincipal());
//        return "/views/single-topic";
//    }

    @GetMapping("/detail/{id}")
    public ModelAndView showDetailTopic(@PathVariable Long id){
        Optional<Topic> topic = topicService.findById(id);
        if (topic.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/views/single-topic");
            modelAndView.addObject("topic", topic.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }

    }
}
