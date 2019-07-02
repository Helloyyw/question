package com.question.service.impl;

import com.question.dao.UserBankMapper;
import com.question.entity.UserBank;
import com.question.service.UserBankService;
import com.question.util.JsonData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserBankServiceImpl implements UserBankService {

    @Resource
    public UserBankMapper userBankMapper;

    @Override
    public JsonData saveUserBankList(List<UserBank> userBank) {
        int i = userBankMapper.insertList(userBank);
          if(i==0){
              JsonData.fail("记录答题失败");
          }
        return JsonData.success("成功记录你的答题：");
    }
}
