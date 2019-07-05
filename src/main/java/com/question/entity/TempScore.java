package com.question.entity;

import java.util.Date;

public class TempScore {
    private Integer id;

    private Integer userId;

    private Integer scoreAdd;

    private Date time;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getScoreAdd() {
        return scoreAdd;
    }

    public void setScoreAdd(Integer scoreAdd) {
        this.scoreAdd = scoreAdd;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}