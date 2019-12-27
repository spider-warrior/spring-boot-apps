package rocketmq.consumer.service;

import lombok.extern.slf4j.Slf4j;
import mq.entity.OrderPaidEvent;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Slf4j
//@Service
@RocketMQMessageListener(topic = "test-topic-2", consumerGroup = "my-consumer_test-topic-2")
public class TestTopic2Service implements RocketMQListener<OrderPaidEvent> {
    @Override
    public void onMessage(OrderPaidEvent orderPaidEvent) {
        log.info("topic: test-topic-2, received orderPaidEvent: {}", orderPaidEvent);
    }
}
