package com.question.dao;



import com.question.entity.UserBank;
import com.question.entity.UserQustion;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserQustionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserQustion record);

    int insertSelective(UserQustion record);

    UserQustion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserQustion record);

    int updateByPrimaryKey(UserQustion record);

    List<Integer> finfByUserId(Integer userId);


}