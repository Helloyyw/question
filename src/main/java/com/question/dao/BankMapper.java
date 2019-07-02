package com.question.dao;

import com.question.entity.Bank;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BankMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Bank record);

    int insertSelective(Bank record);

    Bank selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Bank record);

    int updateByPrimaryKey(Bank record);

    int insertList(List<Bank> bankList);
}