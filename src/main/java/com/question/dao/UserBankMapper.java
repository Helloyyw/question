package com.question.dao;

import com.question.entity.UserBank;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserBankMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserBank record);

    int insertSelective(UserBank record);

    UserBank selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserBank record);

    int updateByPrimaryKey(UserBank record);

    int insertList(List<UserBank> userBank);

  //  List<UserBank> findByQuId(Integer quId);
}