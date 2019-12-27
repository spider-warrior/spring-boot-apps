package rocketmq.consumer.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

//默认情况下 Producer 和 Consumer 的消息轨迹功能是开启的且 trace-topic 为 RMQ_SYS_TRACE_TOPIC
//Consumer 端的消息轨迹 trace-topic 可以在配置文件中配置 rocketmq.consumer.customized-trace-topic 配置项，不需要为在每个 @RocketMQTransactionListener 配置
@Slf4j
//@Service
@RocketMQMessageListener(
    topic = "test-topic-1",
    consumerGroup = "my-consumer_test-topic-1-enable-traced",
    enableMsgTrace = true
)
public class TestTopic1EnableTracedService implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        log.info("topic: test-topic-1, received message: {}", message);
    }
}
