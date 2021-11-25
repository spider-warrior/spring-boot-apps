package kafka.consumer.consumer;

import mq.entity.OrderPaidEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * KafkaMessageConsumer
 * @author <a href="mailto:yangjian@ifenxi.com">研发部-杨建</a>
 * @version V1.0
 * @since 2021-11-25 14:47
 **/
@Component
public class KafkaMessageConsumer {

    @KafkaListener(topics = "my-topic", idIsGroup = false)
    public void listMyTopic(OrderPaidEvent msg, Acknowledgment ack) {
        if(msg.getPaidMoney().doubleValue() > 10) {
            throw new RuntimeException("金额太大: " + msg.getPaidMoney());
        }
        System.out.println("收到消息: " + msg);
        //手动提交
        ack.acknowledge();
    }
}
