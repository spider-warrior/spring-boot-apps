package rocketmq.producer.callback;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

// Define transaction listener with the annotation @RocketMQTransactionListener
@RocketMQTransactionListener(txProducerGroup = "test")
public class TestTransactionalTopicCallback implements RocketMQLocalTransactionListener {

    private boolean bool = true;

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        // ... local transaction process, return rollback, commit or unknown
        //发送消息成功后调用该方法执行业务逻辑,
        // COMMIT表示发送的消息会被消费,
        // ROLLBACK表示发送的消息不会被消费,
        // UNKNOWN表示等待mq-broker回查,执行checkLocalTransaction方法，！！！但是目前rmq4.2.0并没有向producer查询，也就是源码中都没有调用这个接口！！！
        return RocketMQLocalTransactionState.UNKNOWN;
    }

    /**
     * 事务回查这里不知道怎么触发
     * */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        // ... check transaction status and return rollback, commit or unknown
        return RocketMQLocalTransactionState.COMMIT;
    }
}
