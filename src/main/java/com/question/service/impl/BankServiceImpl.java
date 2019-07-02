package com.question.service.impl;

import com.question.dao.BankMapper;
import com.question.entity.Bank;
import com.question.service.BankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
@Slf4j
public class BankServiceImpl implements BankService {
    @Resource
    public BankMapper bankMapper;

    @Override
    public int inserList(List<Bank> bankList) {
        log.info("批量插入开始执行：");
        int i = bankMapper.insertList(bankList);
        return  i;
    }
}
