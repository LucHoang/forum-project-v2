package com.casestudy.controller;

import com.casestudy.model.Reply;
import com.casestudy.model.Topic;
import com.casestudy.service.reply.IReplyService;
import com.casestudy.service.topic.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
@Controller
public class TopicController {
    @Autowired
    private ITopicService topicService;
    @Autowired
    private IReplyService replyService;
    @GetMapping("/detail/{id}")
    public ModelAndView showDetailTopic(@PathVariable Long id){
        Optional<Topic> topic = topicService.findById(id);
        Iterable<Reply> reply = replyService.findAllByTopic(topic.get());
//        topic.get().setTopicId(topic.get().g)
//        topicService.save(topic.get());
        if (topic.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/views/single-topic");
            modelAndView.addObject("topic", topic.get());
            modelAndView.addObject("replies", reply);
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("/error.404");
            return modelAndView;
        }

    }
}
