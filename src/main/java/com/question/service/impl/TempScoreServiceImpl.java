package com.question.service.impl;

import com.question.dao.TempScoreMapper;
import com.question.dao.UserMapper;
import com.question.entity.TempScore;
import com.question.service.TempScoreService;
import com.question.util.JsonData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class TempScoreServiceImpl implements TempScoreService {

    @Resource
    private  TempScoreMapper tempScoreMapper;
    @Resource
    private UserMapper userMapper;

    //保存用户的临时积分请求
    @Override
    public JsonData addTempScore(TempScore tempScore) {
       Date time = new Date();
       tempScore.setTime(time);
        Integer userId = tempScore.getUserId();
        //判断用户是否有未处理的积分
        List<TempScore> tempScore1 = tempScoreMapper.findByUserIdAndStatus(userId);
        if(tempScore1 != null){
            return JsonData.fail("你已向管理员借过积分了，请等待管理员处理");
        }
        //判断该用户是否有未归还的积分
        TempScore byUserId = tempScoreMapper.findByUserId(userId);
        if(byUserId != null){
            return JsonData.fail("你还有未还的请求，请先归还才能借积分");
        }
        int i = tempScoreMapper.insertSelective(tempScore);
        if(i == 0){
            return JsonData.fail("当前借积分的人较多稍后再试！");
        }
        return JsonData.success("你已经成功发起请求等待管理员处理。");
    }
//查询未处理的借积分请求
    @Override
    public JsonData findByStatus() {
        List<TempScore> byStatus = tempScoreMapper.findByStatus();
        if(byStatus.size()==0){
            return JsonData.success(byStatus);
        }
        return JsonData.success(byStatus);
    }
//管理员同意借积分
    @Override
    public JsonData addScore(Integer tempId) {

        TempScore tempScore = tempScoreMapper.selectByPrimaryKey(tempId);
        Integer scoreAdd = tempScore.getScoreAdd();
        Integer userId = tempScore.getUserId();

        int i = userMapper.updateByIdAdd(userId,scoreAdd);
        if(i == 0 ){
            return JsonData.fail("给用户添加积分失败");
        }
        int status = 1;//表示已经处理借积分的请求
         //添加积分成功修改该条信息的状态
        int i1 = tempScoreMapper.UpdateByStatus(status, tempId);


        return JsonData.success("已经成功处理该请求");
    }
//查询用户的所有的接积分的请求 列表
    @Override
    public JsonData refeuse(Integer tempId) {
        int status = 3;
        tempScoreMapper.UpdateByStatus(status,tempId);
        return JsonData.success("已成功拒绝");
    }
    //查询出待归还的借积分请求
    @Override
    public JsonData raback(Integer userId) {
        TempScore byUserId = tempScoreMapper.findByUserId(userId);
        List<TempScore> tempScoreList =  new ArrayList<TempScore>();
        tempScoreList.add(byUserId);
        return JsonData.success(tempScoreList);
    }

    @Override
    public JsonData updateBytempId(Integer tempId) {
        int status = 4;
        int i = tempScoreMapper.UpdateByStatus(status, tempId);

        return JsonData.success("归还成功");
    }

}
