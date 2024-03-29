package kafka.producer.controller;

import cn.t.util.common.DateUtil;
import cn.t.util.common.RandomUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import mq.entity.OrderPaidEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

/**
 * KafkaMessageController
 * send方法返回值为future可以添加回调方法
 * kafkaTemplate默认为异步执行, 同步执行的方式为template.send(topic, data).get()
 *
 * @author <a href="mailto:yangjian@liby.ltd">研发部-杨建</a>
 * @version V1.0
 * @since 2021-11-25 14:49
 **/
@RestController
public class KafkaMessageController {

    private final KafkaTemplate<Object, Object> template;
    private final ObjectMapper objectMapper;

    private final boolean randomMoney = false;
    private final boolean validJson = true;

    @GetMapping("produce")
    public void produce() throws Exception {
        OrderPaidEvent orderPaidEvent = new OrderPaidEvent();
        orderPaidEvent.setOrderId(RandomUtil.randomString(8));
        if(randomMoney) {
            orderPaidEvent.setPaidMoney(new BigDecimal(RandomUtil.randomInt(10, 100)));
        } else {
            orderPaidEvent.setPaidMoney(new BigDecimal(1));
        }
        if(validJson) {
            template.send("my-topic", objectMapper.writeValueAsString(orderPaidEvent));
        } else {
            template.send("my-topic", "currentTime: " + DateUtil.convertToDateTimeString(new Date()));
        }
    }

    public KafkaMessageController(KafkaTemplate<Object, Object> template, ObjectMapper objectMapper) {
        this.template = template;
        this.objectMapper = objectMapper;
    }
}
