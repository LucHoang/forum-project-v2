package com.casestudy.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    private Long topicLike;

    private Long topicDislike;

    private LocalDateTime topicDate;

    @ManyToOne
    private User user;

    @ManyToOne
    private Topic topic;

    public Reply() {
    }

    public Reply(Long replyId, @Size(max = 50) String content, Long topicLike, Long topicDislike, LocalDateTime topicDate, User user, Topic topic) {
        this.replyId = replyId;
        this.content = content;
        this.topicLike = topicLike;
        this.topicDislike = topicDislike;
        this.topicDate = topicDate;
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

    public Long getTopicLike() {
        return topicLike;
    }

    public void setTopicLike(Long topicLike) {
        this.topicLike = topicLike;
    }

    public Long getTopicDislike() {
        return topicDislike;
    }

    public void setTopicDislike(Long topicDislike) {
        this.topicDislike = topicDislike;
    }

    public LocalDateTime getTopicDate() {
        return topicDate;
    }

    public void setTopicDate(LocalDateTime topicDate) {
        this.topicDate = topicDate;
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
