package com.casestudy.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "topics")
public class Topic{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long topicId;

    @NotBlank
    @Size(max = 50)
    private String title;

    @NotBlank
    @Size(max = 1000)
    private String content;

    private LocalDateTime topicDate;

    private Long topicLike;

    private Long topicDislike;

    private Long topicStatus;

    private Long topicView;

    @ManyToOne
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "topics_hastag",
            joinColumns = @JoinColumn(name = "topicId"),
            inverseJoinColumns = @JoinColumn(name = "hastagId"))
    private Set<Hastag> hastags = new HashSet<>();

    @ManyToOne
    private User user;

    public Topic() {

    }

    public Topic(Long topicId, @NotBlank @Size(max = 50) String title, @NotBlank @Size(max = 1000) String content, LocalDateTime topicDate, Long topicLike, Long topicDislike, Long topicStatus, Long topicView, Category category, Set<Hastag> hastags, User user) {
        this.topicId = topicId;
        this.title = title;
        this.content = content;
        this.topicDate = topicDate;
        this.topicLike = topicLike;
        this.topicDislike = topicDislike;
        this.topicStatus = topicStatus;
        this.topicView = topicView;
        this.category = category;
        this.hastags = hastags;
        this.user = user;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long id) {
        this.topicId = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTopicDate() {
        return topicDate;
    }

    public void setTopicDate(LocalDateTime topicDate) {
        this.topicDate = topicDate;
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

    public Long getTopicStatus() {
        return topicStatus;
    }

    public void setTopicStatus(Long topicStatus) {
        this.topicStatus = topicStatus;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Hastag> getRoles() {
        return hastags;
    }

    public void setRoles(Set<Hastag> roles) {
        this.hastags = roles;
    }

    public Set<Hastag> getHastags() {
        return hastags;
    }

    public void setHastags(Set<Hastag> hastags) {
        this.hastags = hastags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getTopicView() {
        return topicView;
    }

    public void setTopicView(Long topicView) {
        this.topicView = topicView;
    }

    public String getSimpleDate(){
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String formatDateTime = this.topicDate.format(format);
            return formatDateTime;
        }catch (Exception e){
            return null;
        }
    }

}
