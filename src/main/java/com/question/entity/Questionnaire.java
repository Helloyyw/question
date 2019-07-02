package com.question.entity;

import lombok.Builder;

@Builder
public class Questionnaire {

    private Integer id;

    private String name;

    private Integer userId;

    private String scroe;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getScroe() {
        return scroe;
    }

    public void setScroe(String scroe) {
        this.scroe = scroe == null ? null : scroe.trim();
    }
}