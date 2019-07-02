package com.question.service.impl;

import com.question.dao.UserMapper;
import com.question.entity.User;
import com.question.service.UserService;
import com.question.util.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl  implements UserService {

    @Resource
    public UserMapper userMapper;
    //登录验证用户信息
    @Override
    public User check(String username, String password) {
        log.info("name:{}",username);

        return userMapper.check(username,password);
    }
//修改用户信息
    @Override
    public Integer updateUserInfo(User user) {
        log.info("user:{}",user);

        return userMapper.updateByPrimaryKey(user);
    }
//查询所有用户
    @Override
    public List<User> findAllUserList() {
        return userMapper.findAllUserList();
    }
//删除用户
    @Override
    public JsonData deleUser(Integer userId) {

        int result = userMapper.deleteByPrimaryKey(userId);

        if(result != 1){
            return JsonData.fail("删除失败！请联系管理员。。");
        }
        return JsonData.success("你已经成功删除一条用户数据");
    }
//添加用户
    @Override
    public JsonData addUser(User user) {
        int i = userMapper.insertSelective(user);
        if(i !=1){
            JsonData.fail("添加用户失败");

        }
        return JsonData.success("你已经成功添加一条数据");
    }

    @Override
    public User findById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
