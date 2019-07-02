package com.question.controller;

import com.question.dto.QuestionBankDto;
import com.question.service.QuestionService;
import com.question.util.JsonData;
import com.question.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
@Slf4j
public class UserQuestionController {
    @Resource
    public QuestionService questionService;

    //添加问卷
    @PostMapping("/add")
    public JsonData addQuestion(QuestionBankDto questionBankDto){

        log.info("dto对象：{}", JsonMapper.obj2String(questionBankDto));

        JsonData jsonData = questionService.addQuestion(questionBankDto);

        return jsonData;
    }



}
