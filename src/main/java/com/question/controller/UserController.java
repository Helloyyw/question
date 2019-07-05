package com.question.controller;

import com.question.entity.TempScore;
import com.question.entity.User;
import com.question.service.TempScoreService;
import com.question.service.UserService;
import com.question.util.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
@CrossOrigin("*")
public class UserController {

    @Resource
    public UserService userService;
    @Resource
    public TempScoreService tempScoreService;
    /**
     * 用户登录验证
     *
     * @param username
     * @param password
     * @return
     */

    @PostMapping("/dologin")
    public JsonData dologin(String username,  String password) {
        User user = userService.check(username, password);
        if (user == null) {
            return JsonData.fail("用户名或密码错误");
        }
        return JsonData.success(user);
    }
    /**
     *根据id查询用户信息
     *
     * @return
     */
    @RequestMapping("/findById")
    public JsonData findById(Integer id) {
        User user = userService.findById(id);
        if (user == null) {
            return JsonData.fail("用户不存在");
        }
        return JsonData.success(user);
    }
    /**
     *查询用户列表
     *
     * @return
     */
    @RequestMapping("/findAll")
    public JsonData findAllUserList() {
        List<User> allUserList = userService.findAllUserList();
        if (allUserList == null) {
            return JsonData.fail("当前没有发现任何用户");
        }
        return JsonData.success(allUserList);
    }
    /**
     *更新用户功能
     * @param user
     * @return
     */
    @PostMapping("/update")
    public JsonData updateUserInfo(User user) {
        int userInfo = userService.updateUserInfo(user);
        if(userInfo != 1){
            return JsonData.fail("更新失败！请联系管理员。。");
        }
        return JsonData.success("更新完成");
    }
    /**
     *删除用户功能
     * @param userId
     * @return
     */
    @RequestMapping("/delete")
    public JsonData updateUserInfo(Integer userId) {
        log.info("用户id:{}",userId);
        return  userService.deleUser(userId);
    }
    /**
     *添加用户功能
     * @param
     * @return
     */
    @PostMapping("/add")
    public JsonData add( User user) {

        return  userService.addUser(user);
    }
    /**
     * 查询当前用户自己的问卷信息
     */
    @RequestMapping("/findUserQuestion")
    public JsonData findUserQuestion() {
        List<User> allUserList = userService.findAllUserList();
        if (allUserList == null) {
            return JsonData.fail("当前没有发现任何用户");
        }
        return JsonData.success(allUserList);
    }

    @PostMapping("/register")
    public JsonData registerUserInfo(User user) {
        log.info("user:{}",user);
        JsonData jsonData = userService.addUser(user);
        return JsonData.success("注册成功");
    }

    //向管理员借积分功能
    @PostMapping("/borrow")
    public JsonData borrowScore(TempScore tempScore) {
        log.info("user:{}",tempScore);
        JsonData jsonData = tempScoreService.addTempScore(tempScore);
        return jsonData;
    }
    //管理员获取所有状态没有处理的借积分请求
    @GetMapping("/getNoDeal")
    public JsonData getNoDeal() {
        JsonData jsonData = tempScoreService.findByStatus();
        return jsonData;
    }
    //管理员处理用户的借积分请求
    @GetMapping("/addScore")
    public JsonData addScore(Integer tempId) {
        JsonData jsonData = tempScoreService.addScore(tempId);
        return jsonData;
    }
    //管理员拒绝该用户请求积分的请求
    @GetMapping("/refeuse")
    public JsonData refeuse(Integer  tempId) {
        JsonData jsonData = tempScoreService.refeuse(tempId);
        return jsonData;
    }
    //用户应该归还积分列表
    @GetMapping("/raback")
    public JsonData raback(Integer  userId) {
        JsonData jsonData = tempScoreService.raback(userId);
        return jsonData;
    }
    //用户点击归还积分
    @GetMapping("/raback1")
    public JsonData raback1(Integer  tempId) {
        JsonData jsonData = tempScoreService.updateBytempId(tempId);
        return jsonData;
    }
}
