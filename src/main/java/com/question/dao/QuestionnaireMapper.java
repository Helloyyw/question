package com.question.dao;

import com.question.entity.Questionnaire;
import com.question.util.JsonData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionnaireMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Questionnaire record);

    int insertSelective(Questionnaire record);

    Questionnaire selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Questionnaire record);

    int updateByPrimaryKey(Questionnaire record);

    List<Questionnaire> findAll();

    List<Questionnaire> finfByUserId(Integer userId);

}