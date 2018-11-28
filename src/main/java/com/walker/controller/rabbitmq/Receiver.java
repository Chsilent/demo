package com.walker.controller.rabbitmq;

import com.walker.bean.User;
import com.walker.common.constant.Constant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 接收rabbitmq的消息
 *
 * @author Walker
 * @date 2018/11/4 下午10:31
 */
@Component
@RabbitListener(queues = Constant.RABBIT_MQ_NAME)
public class Receiver {

    @RabbitHandler
    public void receive(String msg) {
        System.out.println("receive msg:" + msg);
    }

    @RabbitHandler
    public void receiveObject(List<?> list) {
        List<User> userList = (List<User>) list;
        System.out.println(Arrays.toString(userList.toArray()));
    }
}
