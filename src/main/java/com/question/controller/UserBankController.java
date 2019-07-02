package com.question.controller;

import com.question.entity.UserBank;
import com.question.service.UserBankService;
import com.question.util.JsonData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("userbank")
public class UserBankController  {
    @Resource
    public UserBankService userBankService;

    //填写
    @PostMapping("save")
    public JsonData saveUserBank(List<UserBank> userBanklist){
        if(userBanklist == null){
            JsonData.fail("后台没有获取到你填的问卷信息");
        }

        JsonData jsonData = userBankService.saveUserBankList(userBanklist);

        return JsonData.success("问卷填写成功！系统会自动给你加上积分");
    }
}
