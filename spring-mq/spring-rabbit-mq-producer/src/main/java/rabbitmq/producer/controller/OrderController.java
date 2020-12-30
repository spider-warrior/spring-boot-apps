package rabbitmq.producer.controller;

import cn.t.util.common.RandomUtil;
import mq.entity.OrderPaidEvent;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @version V1.0
 * @since 2020-12-30 19:56
 **/
@RequestMapping("order")
@RestController
public class OrderController {

    private final RabbitTemplate rabbitTemplate;

    private final RabbitTemplate.ConfirmCallback confirmCallback = (correlationData, ack, cause) -> {
        System.out.println("correlationData: " + correlationData);
        String messageId = correlationData.getId();
        if(ack) {
            //如果confirm返回成功则进行更新
            System.out.println(messageId + ", 消息发送成功,更新消息状态: 1");
        } else {
            //失败则进行具体的后续操作: 重试或者补偿手段
            System.out.println("异常处理");
        }
    };

    @GetMapping("sendOrderPaidMessage")
    public void sendOrderPaidMessage() {
        rabbitTemplate.setConfirmCallback(confirmCallback);
        OrderPaidEvent event = new OrderPaidEvent();
        event.setOrderId(RandomUtil.randomString(16));
        event.setPaidMoney(BigDecimal.valueOf(RandomUtil.randomInt(100, 1000)));
        CorrelationData correlationData = new CorrelationData(RandomUtil.randomString(32));
        rabbitTemplate.convertAndSend("order-exchange", //exchange
            //单词匹配 order.* 匹配 order.xxx
            //多词匹配 order.# 匹配 order.xxx, order.xxx.aaa
            "order.paid",   //route key
            event, //消息体
            correlationData //消息唯一ID
        );
    }

    public OrderController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
}
