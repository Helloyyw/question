package com.question.controller;

import com.question.dto.QuestionBankDto;
import com.question.entity.UserQustion;
import com.question.service.QuestionService;
import com.question.util.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
@Slf4j
public class UserQuestionController {
    @Resource
    public QuestionService questionService;

    //添加问卷
    @PostMapping("/add")
    public JsonData addQuestion(@RequestBody  QuestionBankDto questionBankDto){

      //  log.info("dto对象：{}", JsonMapper.obj2String(questionBankDto));
        log.info("dto对象：{}", questionBankDto);
        if(questionBankDto == null){
            return JsonData.fail("添加失败 ，后台检测不到你输入的数据");
        }
        JsonData jsonData = questionService.addQuestion(questionBankDto);

        return jsonData;
    }
    //查询所有的问卷列表
    @GetMapping("/findAll")
    public JsonData findAll(){

        return questionService.findAll();
    }
    //删除问卷
    //查询所有的问卷列表
    @GetMapping("/delete")
    public JsonData deleteById(Integer id){

        return questionService.deleteById(id);
    }
    //根据问卷对应的id查询到问卷的详细题集合
    @GetMapping("/getBank")
    public JsonData findBankById(Integer quId){

        return questionService.findBankById(quId);
    }
   //根据用户id来查询用户自己有哪些问卷
   @GetMapping("/findByUserId")
   public JsonData findByUserId(Integer userId){

       return questionService.findByUserId(userId);
   }
    //根据用户id查询已答问卷
    @GetMapping("/findUserIded")
    public JsonData findUserIded(Integer userId){

         return questionService.findUserIded(userId);
    }

    //查询用户没有填写的问卷列表
    @GetMapping("/findNoWirte")
    public JsonData findNoWirte(Integer userId){

        return questionService.findNoWirte(userId);
    }
    //保存用户的答案信息
    @PostMapping("/saveAnswer")
    public JsonData saveAswer(UserQustion userQustion){
            log.info("UserQustion:{}",userQustion.getQuId());
        return questionService.saveAswer(userQustion);
    }

}
