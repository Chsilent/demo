package com.walker.controller;

import com.walker.bean.User;
import com.walker.bean.UserEntity;
import com.walker.common.utils.ResultInfo;
import com.walker.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User Controller
 *
 * @author Walker
 * @date 2018/10/18 下午10:39
 */
@RestController
@RequestMapping("/Users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 查询所有的用户
     *
     * @return
     */
    @RequestMapping("/list")
    public Object list() {
        ResultInfo resultInfo = new ResultInfo();
        List<User> userList = userService.getAllUsers();
        resultInfo.setData(userList);
        return resultInfo;
    }

    /**
     * 测试springboot redis操作
     *
     * @return
     */
    @RequestMapping("/testRedis")
    public Object testRedis() {
        ResultInfo resultInfo = new ResultInfo();
        List<User> userList = userService.testRedis();
        resultInfo.setData(userList);
        return resultInfo;
    }

    /**
     * mongodb:创建用户
     *
     * @param user
     * @return
     */
    @PostMapping("/save")
    public Object save(@RequestBody UserEntity user) {
        ResultInfo resultInfo = new ResultInfo();
        userService.save(user);
        return resultInfo;
    }

    /**
     * mongodb:根据用户姓名查询用户
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public Object query(@RequestParam String name) {
        ResultInfo resultInfo = new ResultInfo();
        UserEntity user = userService.queryByName(name);
        resultInfo.setData(user);
        return resultInfo;
    }

    /**
     * mongodb:更新用户
     *
     * @param user
     * @return
     */
    @PostMapping("/update")
    public Object update(@RequestBody UserEntity user) {
        ResultInfo resultInfo = new ResultInfo();
        int result = userService.update(user);
        Map<String, Integer> map = new HashMap<>(16);
        map.put("updateResult", result);
        resultInfo.setData(map);
        return resultInfo;
    }

    /**
     * mongodb:根据id删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Object delete(@RequestParam String id) {
        ResultInfo resultInfo = new ResultInfo();
        int result = userService.delete(id);
        Map<String, Integer> map = new HashMap<>(16);
        map.put("deleteResult", result);
        resultInfo.setData(map);
        return resultInfo;
    }

}
