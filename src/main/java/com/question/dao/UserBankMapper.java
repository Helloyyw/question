package com.question.dao;

import com.question.entity.UserBank;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserBankMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserBank record);

    int insertSelective(UserBank record);

    UserBank selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserBank record);

    int updateByPrimaryKey(UserBank record);
}