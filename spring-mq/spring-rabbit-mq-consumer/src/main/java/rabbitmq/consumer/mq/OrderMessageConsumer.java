package rabbitmq.consumer.mq;

import com.rabbitmq.client.Channel;
import mq.entity.OrderPaidEvent;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @version V1.0
 * @since 2020-12-30 20:34
 **/
@Component
public class OrderMessageConsumer {

    //该注解会自动在broker中创建exchange,queue并绑定关系
    @RabbitListener(bindings = @QueueBinding(
        value = @Queue("order-queue"),
        exchange = @Exchange(name = "order-exchange", type = "topic"),
        key = "order.*"
    ))
    @RabbitHandler
    public void onOrderMessage(@Payload OrderPaidEvent event,
                               @Headers Map<String, Object> headers,
                               Channel channel) throws Exception {
        System.out.println("------------------------------------收到消息, 开始消费------------------------------------");
        System.out.println("订单ID: " + event.getOrderId());

        Long deliveryTag = (Long)headers.get(AmqpHeaders.DELIVERY_TAG);
        //ACK
        channel.basicAck(deliveryTag, false);
    }

}
