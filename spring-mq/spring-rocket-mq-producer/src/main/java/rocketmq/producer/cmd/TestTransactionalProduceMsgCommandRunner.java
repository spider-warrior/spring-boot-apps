package rocketmq.producer.cmd;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TestTransactionalProduceMsgCommandRunner implements CommandLineRunner {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void run(String... args) {
        // Build a SpringMessage for sending in transaction
        Message msg = MessageBuilder.withPayload("this is a transactional msg").build();
        // In sendMessageInTransaction(), the first parameter transaction name ("test") must be same with the @RocketMQTransactionListener's member field 'transName'
        rocketMQTemplate.sendMessageInTransaction("test", "test-transaction-topic", msg, null);
    }
}
