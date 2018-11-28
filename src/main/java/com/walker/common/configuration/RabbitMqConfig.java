package com.walker.common.configuration;

import com.walker.common.constant.Constant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq配置
 * 配置队列的名称，发送者和接受者的名称必须一致，否则接收不到消息
 *
 * @author Walker
 * @date 2018/11/4 下午10:15
 */
@Configuration
public class RabbitMqConfig {

    @Bean
    public Queue queue() {
        return new Queue(Constant.RABBIT_MQ_NAME);
    }
}
