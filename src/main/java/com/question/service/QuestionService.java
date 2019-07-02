package com.question.service;

import com.question.dto.QuestionBankDto;

import com.question.entity.UserQustion;
import com.question.util.JsonData;

public interface QuestionService {

    JsonData addQuestion(QuestionBankDto questionnaire);

    JsonData findAll();

    JsonData findBankById(Integer quId);

    JsonData findByUserId(Integer userId);

    JsonData deleteById(Integer id);

    JsonData findUserIded(Integer userId);


    JsonData findNoWirte(Integer userId);

    JsonData saveAswer(UserQustion userQustion);
}
