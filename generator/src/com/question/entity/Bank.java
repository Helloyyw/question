package com.question.entity;

public class Bank {
    private Integer id;

    private String title;

    private String answer;

    private Integer quId;

    private String selectitem;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public Integer getQuId() {
        return quId;
    }

    public void setQuId(Integer quId) {
        this.quId = quId;
    }

    public String getSelectitem() {
        return selectitem;
    }

    public void setSelectitem(String selectitem) {
        this.selectitem = selectitem == null ? null : selectitem.trim();
    }
}