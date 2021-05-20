package com.casestudy.controller;

import com.casestudy.model.Reply;
import com.casestudy.model.Topic;
import com.casestudy.model.User;
import com.casestudy.service.reply.IReplyService;
import com.casestudy.service.topic.ITopicService;
import com.casestudy.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebMethod;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/detail")
public class TopicDetailController {
    @Autowired
    private ITopicService topicService;
    @Autowired
    private IReplyService replyService;
    @Autowired
    private IUserService userService;

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
    @GetMapping("/{id}")
    public ModelAndView showDetailTopic(@PathVariable Long id) {
        Optional<User> userCurrent = userService.findByUsername(getPrincipal());
        Optional<Topic> topic = topicService.findById(id);
        Iterable<Reply> reply = replyService.findAllByTopic(topic.get());
        Iterable<Topic> topTopics = topicService.findTopByTopicLike();
        topic.get().setTopicView(topic.get().getTopicView() + 1); // tang moi khi an vao detail topic
        topicService.save(topic.get());
        ModelAndView modelAndView = new ModelAndView("/views/single-topic");
        modelAndView.addObject("topic", topic.get());
        modelAndView.addObject("topTopic", topTopics);
        modelAndView.addObject("replies", reply);
        if (userCurrent.isPresent()){
            modelAndView.addObject("userCurrent", userCurrent.get());
        }
        return modelAndView;
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<Reply> createReply(@RequestBody Reply reply, @PathVariable Long id) {
        Optional<User> userCurrent = userService.findByUsername(getPrincipal());
        reply.setTopic(topicService.findById(id).get());
        reply.setUser(userCurrent.get());
        reply.setReplyLike(0L);
        reply.setReplyDislike(0L);
        reply.setCommentId(0L);
        reply.setReplyDate(LocalDateTime.now());
        return new ResponseEntity<>(replyService.save(reply), HttpStatus.CREATED);
    }
    @PostMapping("/reply/{id}")
    public ResponseEntity<Reply> createNewReply(@RequestBody Reply reply, @PathVariable Long id) {
        Optional<User> userCurrent = userService.findByUsername(getPrincipal());
        reply.setTopic(topicService.findById(id).get());
        reply.setUser(userCurrent.get());
        reply.setReplyLike(0L);
        reply.setReplyDislike(0L);
        reply.setCommentId(0L);
        reply.setReplyDate(LocalDateTime.now());
        return new ResponseEntity<>(replyService.save(reply), HttpStatus.CREATED);
    }
    @GetMapping("/create/{id}")
    public ResponseEntity<Iterable<Reply>> allReply(@PathVariable Long id) {
        Optional<Topic> topic = topicService.findById(id);
        return new ResponseEntity<>(replyService.findAllByTopic(topic.get()),HttpStatus.OK);
    }
}