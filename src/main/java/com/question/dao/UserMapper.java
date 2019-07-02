package com.question.dao;

import com.question.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    Integer updateByPrimaryKey(User record);

    User check(@Param("username") String username,@Param("password") String password);

    List<User> findAllUserList();
}