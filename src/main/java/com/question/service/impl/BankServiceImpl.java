package com.question.service.impl;

import com.question.dao.BankMapper;
import com.question.dto.BankDto;
import com.question.entity.Bank;
import com.question.service.BankService;
import com.question.util.JsonData;
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
//根据问卷id查询题库集合
    @Override
    public List<Bank> findBankById(Integer quId) {

        List<Bank> bankById = bankMapper.findBankById(quId);
        return bankById;
    }
    //根据问卷id查询题库集合
    @Override
    public JsonData findByQuId(Integer quId) {
        List<Bank> bankById = bankMapper.findBankById(quId);
          if(bankById.size()==0){
              return  JsonData.success("当前问卷中没有题目哦");
          }
        return JsonData.success(bankById);
    }
}
