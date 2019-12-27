package rocketmq.consumer;

import org.apache.rocketmq.client.log.ClientLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RocketMqConsumer {
    public static void main(String[] args) {
        System.setProperty(ClientLogger.CLIENT_LOG_USESLF4J, "true");
        SpringApplication.run(RocketMqConsumer.class, args);
    }
}
