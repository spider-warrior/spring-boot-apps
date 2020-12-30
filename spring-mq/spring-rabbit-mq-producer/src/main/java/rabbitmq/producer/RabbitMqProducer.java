package rabbitmq.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RabbitMqProducer {

    public static void main(String[] args) {
        SpringApplication.run(RabbitMqProducer.class, args);
    }
}
