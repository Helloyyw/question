package com.question.service;


import com.question.entity.Bank;
import com.question.util.JsonData;

import java.util.List;

public interface BankService {

    int inserList(List<Bank> bankList);
    List<Bank> findBankById(Integer quId);


    JsonData findByQuId(Integer quId);
}
