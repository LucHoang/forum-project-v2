package com.casestudy.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "hastag")
public class Hastag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hastagId;

    @NotBlank
    @Size(max = 50)
    private String hastagName;

    private String color;

    public Hastag() {
    }
    @ManyToMany(mappedBy = "hastags")
    private Set<Topic> topics = new HashSet<>();

    public Hastag(Long hastagId, @NotBlank @Size(max = 50) String hastagName, String color) {
        this.hastagId = hastagId;
        this.hastagName = hastagName;
        this.color = color;
    }

    public Hastag(@NotBlank @Size(max = 50) String hastagName, Set<Topic> topics) {
        this.hastagName = hastagName;
        this.topics = topics;
    }

    public Hastag(@NotBlank @Size(max = 50) String hastagName) {
        this.hastagName = hastagName;
    }

    public Long getHastagId() {
        return hastagId;
    }

    public void setHastagId(Long hastagId) {
        this.hastagId = hastagId;
    }

    public String getHastagName() {
        return hastagName;
    }

    public void setHastagName(String name) {
        this.hastagName = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
