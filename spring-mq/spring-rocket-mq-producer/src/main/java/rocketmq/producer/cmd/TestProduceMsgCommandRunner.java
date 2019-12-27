package rocketmq.producer.cmd;

import mq.entity.OrderPaidEvent;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.math.BigDecimal;

//@Component
public class TestProduceMsgCommandRunner implements CommandLineRunner {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void run(String... args) throws Exception {
        //顺序消费
        rocketMQTemplate.sendOneWayOrderly("test-order-topic", "this is the order topic content", "order");
        rocketMQTemplate.convertAndSend("test-topic-1", "Hello, World!");
        rocketMQTemplate.send("test-topic-1", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
        rocketMQTemplate.convertAndSend("test-topic-2", new OrderPaidEvent("T_001", new BigDecimal("88.00")));

//        rocketMQTemplate.destroy(); // notes:  once rocketMQTemplate be destroyed, you can not send any message again with this rocketMQTemplate
    }
}
