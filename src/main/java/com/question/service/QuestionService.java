package com.question.service;

import com.question.dto.QuestionBankDto;

import com.question.util.JsonData;

public interface QuestionService {

    JsonData addQuestion(QuestionBankDto questionnaire);
}
