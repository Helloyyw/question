package com.question.dao;

import com.question.entity.TempScore;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface TempScoreMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TempScore record);

    int insertSelective(TempScore record);

    TempScore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TempScore record);

    int updateByPrimaryKey(TempScore record);

    List<TempScore> findByStatus();

    TempScore findByUserId(Integer userId);

    int UpdateByStatus(int status,int tempId);

    List<TempScore> findByUserIdAndStatus(Integer userId);
}