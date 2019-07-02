package com.question.service;

import com.question.entity.User;
import com.question.util.JsonData;

import java.util.List;


public interface UserService {


    User check(String username, String password);
    Integer updateUserInfo(User user);

    List<User> findAllUserList();

    JsonData deleUser(Integer userId);

    JsonData addUser(User user);

    User findById(Integer id);
}
