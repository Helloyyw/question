package com.question.service;

import com.question.entity.TempScore;
import com.question.util.JsonData;

public interface TempScoreService {
   JsonData addTempScore(TempScore tempScore);

    JsonData findByStatus();

    JsonData addScore(Integer tempId);

    JsonData refeuse(Integer tempId);

    JsonData raback(Integer userId);

    JsonData updateBytempId(Integer tempId);
}
