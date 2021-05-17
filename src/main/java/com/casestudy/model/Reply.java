package com.casestudy.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "replies")
public class Reply{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    @Size(max = 50)
    private String content;

    private Long replyLike;

    private Long replyDislike;

    private LocalDateTime replyDate;

    @ManyToOne
    private User user;

    @ManyToOne
    private Topic topic;

    public Reply() {
    }

    public Reply(Long replyId, @Size(max = 50) String content, Long replyLike, Long replyDislike, LocalDateTime replyDate, User user, Topic topic) {
        this.replyId = replyId;
        this.content = content;
        this.replyLike = replyLike;
        this.replyDislike = replyDislike;
        this.replyDate = replyDate;
        this.user = user;
        this.topic = topic;
    }

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getReplyLike() {
        return replyLike;
    }

    public void setReplyLike(Long topicLike) {
        this.replyLike = topicLike;
    }

    public Long getReplyDislike() {
        return replyDislike;
    }

    public void setReplyDislike(Long topicDislike) {
        this.replyDislike = topicDislike;
    }

    public LocalDateTime getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(LocalDateTime topicDate) {
        this.replyDate = topicDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
