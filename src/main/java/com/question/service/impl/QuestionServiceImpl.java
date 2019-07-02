package com.question.service.impl;

import com.question.dao.QuestionnaireMapper;
import com.question.dto.QuestionBankDto;
import com.question.entity.Bank;
import com.question.entity.Questionnaire;
import com.question.service.BankService;
import com.question.service.QuestionService;
import com.question.util.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class QuestionServiceImpl implements QuestionService {

    @Resource
    public QuestionnaireMapper questionnaireMapper;
    @Resource
    public BankService bankService;

    //添加题库功能
    @Override
    public JsonData addQuestion(QuestionBankDto questionBankDto) {
        log.info("进入添加题库页面 ");
        if(questionBankDto == null){
           return JsonData.fail("添加失败 ，后台检测不到你输入的数据");
        }
        //构建问卷对象
        Questionnaire questionnaire = Questionnaire.builder().name(questionBankDto.getName())
                .userId(questionBankDto.getUserId()).scroe(questionBankDto.getScroe())
                .build();


        int i = questionnaireMapper.insertSelective(questionnaire);
        if(i != 1){
            return   JsonData.fail("添加问卷失败请联系管理员");
        }
        //获取题库集合
        List<Bank> bankList = questionBankDto.getBankList();


        if(bankList == null){
            return   JsonData.fail("当前题库为空");
        }
        //设置该题属于哪个问卷
        for(Bank bank :bankList){
            bank.setQuId(i);
        }

        //开始批量插入题库
        int i1 = bankService.inserList(bankList);
         if(i1 == 0){
             return   JsonData.fail("添加题库失败请联系管理员");
         }
        log.info("添加题库数量：{} ",i1);

        return  JsonData.success("添加问卷成功！");
    }
}
