package com.walker.controller.rabbitmq;

import com.walker.bean.User;
import com.walker.common.constant.Constant;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * rabbitmq 发送controller
 * 生产者产生消息通过该类发送消息
 *
 * @author Walker
 * @date 2018/11/4 下午10:18
 */
@RestController
@RequestMapping("rabbitmq")
public class SendController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * 发送消息
     *
     * @return
     */
    @RequestMapping("/send")
    public Object send() {
        /*//发送字符
        String content = "Date:" + new Date();
        amqpTemplate.convertAndSend(Constant.RABBIT_MQ_NAME, content);
        return content;*/
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(i + 1);
            user.setAge(18 + i);
            user.setName("walker_" + i);
            user.setPasswd("walker_" + i);
            userList.add(user);
        }
        //发送对象
        amqpTemplate.convertAndSend(Constant.RABBIT_MQ_NAME, userList);
        return userList;
    }
}
