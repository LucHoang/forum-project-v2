package com.casestudy.controller;

import com.casestudy.model.Reply;
import com.casestudy.model.Topic;
import com.casestudy.service.reply.IReplyService;
import com.casestudy.service.topic.ITopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
//    @GetMapping
//    public ResponseEntity<Iterable<Reply>> allReply() {
//        return new ResponseEntity<>(replyService.findAll(), HttpStatus.OK);
//    }
//    @PostMapping
//    public ResponseEntity<Reply> createReply(@RequestBody Reply reply) {
//        return new ResponseEntity<>(replyService.save(reply), HttpStatus.CREATED);
//    }
}
