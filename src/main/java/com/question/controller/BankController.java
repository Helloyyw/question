package com.question.controller;

import com.question.service.BankService;
import com.question.util.JsonData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Resource
    public BankService bankService;

    //根据问卷的id 来获取对应的题库集合信息
    @GetMapping("/findByQuId")
    public JsonData findByQuId(Integer quId){
        return bankService.findByQuId(quId);
    }
}
