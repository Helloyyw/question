package com.question.service.impl;


import com.question.dao.QuestionnaireMapper;
import com.question.dao.UserBankMapper;
import com.question.dao.UserMapper;
import com.question.dao.UserQustionMapper;
import com.question.dto.BankDto;
import com.question.dto.QuestionBankDto;
import com.question.entity.*;
import com.question.service.BankService;
import com.question.service.QuestionService;
import com.question.util.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class QuestionServiceImpl implements QuestionService {

    @Resource
    public QuestionnaireMapper questionnaireMapper;
    @Resource
    public UserQustionMapper userQustionMapper;
    @Resource
    public BankService bankService;
    @Resource
    public UserMapper userMapper;
    @Resource
    public UserBankMapper userBankMapper;
    //添加题库功能
    @Override
    public JsonData addQuestion(QuestionBankDto questionBankDto) {
        log.info("进入添加题库页面 ");
        //构建问卷对象
        Questionnaire questionnaire = Questionnaire.builder().name(questionBankDto.getName())
                .userId(questionBankDto.getUserId()).scroe(questionBankDto.getScroe())
                .build();
        //用户在进行 发问卷的功能的时候必须判断用户的积分情况
        User user = userMapper.selectByPrimaryKey(questionBankDto.getUserId());
        if(user.getScore() < 5){
            return   JsonData.fail("你的积分不足，还不能发表问卷");
        }
    //插入一条问卷
        int i = questionnaireMapper.insertSelective(questionnaire);

        if(i != 1){
            return   JsonData.fail("添加问卷失败请联系管理员");
        }
        //插入成功后就根据问卷id批量插入该问卷的题库
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
        log.info("添加题库数量为：{} ",i1);
        //问卷的操作完成后需要扣减用户的积分
       int core =  userMapper.updateById(questionBankDto.getUserId());
       if(core != 1){
           return   JsonData.fail("扣减积分失败，请联系管理员");
       }
        return  JsonData.success("添加问卷成功！");
    }

    @Override
    public JsonData findAll() {
      List<Questionnaire> questionnaireList = questionnaireMapper.findAll();
      log.info("问卷集合：{}",questionnaireList);


      if(questionnaireList.size()==0){
          return JsonData.fail("当前没有查询到任何问卷");
      }
        return JsonData.success(questionnaireList);
    }
//获取详细的题库集合
    @Override
    public JsonData findBankById(Integer quId) {

        List<Bank> bankById = bankService.findBankById(quId);


        if(bankById == null){
            return JsonData.fail("题库获取失败，请联系管理员");
        }

        return JsonData.success(bankById);
    }
    //通过问卷id查问卷
    @Override
    public JsonData findByUserId(Integer userId) {
        List<Questionnaire> questionnaireList = questionnaireMapper.finfByUserId(userId);

        if(questionnaireList == null){
            return JsonData.fail("没有数据哦");
        }
        return JsonData.success(questionnaireList);
    }
//删除问卷
    @Override
    public JsonData deleteById(Integer id) {
        int i = questionnaireMapper.deleteByPrimaryKey(id);
        if(i ==0 ){
            return JsonData.fail("删除失败！") ;
        }
        return JsonData.success("删除成功");
    }
//查询用户填过得问卷集合
    @Override
    public JsonData findUserIded(Integer userId) {
        //先根据用户id查询出问卷id集合
        List<Integer> quIdList = userQustionMapper.finfByUserId(userId);
        if(quIdList.size()==0){
            return JsonData.success("当前用户没有填过任何问卷");
        }
        //然后根据用户的填写的问卷id集合查询出所有的问卷信息
       List<Questionnaire> questionnaireList = new ArrayList<>();
       for(Integer integer : quIdList){
           Questionnaire questionnaire = questionnaireMapper.selectByPrimaryKey(integer);
           questionnaireList.add(questionnaire);
       }
        return JsonData.success(questionnaireList);
    }

    @Override
    public JsonData findNoWirte(Integer userId) {
        //查询所有的问卷
        List<Questionnaire> questionnaireList = questionnaireMapper.findAll();
        //查询用户已经填写的问卷
        List<Questionnaire> qq = (List<Questionnaire>)findUserIded(userId).getData();
        boolean b = questionnaireList.removeAll(qq);

        return JsonData.success(questionnaireList);
    }
//保存用户答案
    @Override
    public JsonData saveAswer(UserQustion userQustion) {
        int i = userQustionMapper.insertSelective(userQustion);
        //更新积分
        Questionnaire questionnaire = questionnaireMapper.selectByPrimaryKey(userQustion.getQuId());
                       userMapper.updateByIdAdd(userQustion.getUserId(),Integer.parseInt(questionnaire.getScroe()));
        return JsonData.success("存入成功！");
    }
}
