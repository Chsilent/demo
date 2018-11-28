package com.walker.controller.jpa;

import com.walker.bean.User;
import com.walker.common.utils.ResultInfo;
import com.walker.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Jpa操作Controller
 *
 * @author Walker
 * @date 2018/11/12 下午2:34
 */
@RestController
@RequestMapping("/jpa")
public class JpaController {

    private static final Logger logger = LoggerFactory.getLogger(JpaController.class);

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("findAll")
    public Object findAllUser() {
        ResultInfo resultInfo = new ResultInfo();
        List<User> userList = userRepository.findAll();
        resultInfo.setData(userList);
        return resultInfo;
    }
}
