package com.question.service;

import com.question.entity.UserBank;
import com.question.util.JsonData;


import java.util.List;


public interface UserBankService {

    JsonData saveUserBankList(List<UserBank> userBank);
}
